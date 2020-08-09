package com.example.cyjlog.config;

import ch.qos.logback.classic.spi.*;
import ch.qos.logback.core.CoreConstants;
import ch.qos.logback.core.db.DBAppenderBase;
import ch.qos.logback.core.db.DBHelper;
import com.example.cyjcommon.config.SqlBuilder;

import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.*;

import static ch.qos.logback.core.db.DBHelper.closeStatement;

/**
 * @author 曹元杰
 * @version 1.0
 * @date 2020/1/21 14:46
 */
@SuppressWarnings("ALL")
public class ServerLogAppender extends DBAppenderBase<ILoggingEvent> {

    protected String insertPropertiesSql;
    protected String insertExceptionSql;
    protected static final Method GET_GENERATED_KEYS_METHOD;

    protected String insertSql;
    /**
     * id
     */
    static final int UUID_INDEX = 1;
    /**
     * 应用的端口
     */
    static final int APP_INDEX = 2;
    /**
     * 类名
     */
    static final int CLASS_INDEX = 3;
    /**
     * 路径
     */
    static final int CLASSPATH_INDEX = 4;
    /**
     * 方法名
     */
    static final int METHOD_INDEX = 5;
    /**
     * 线程名
     */
    static final int THREADNAME_INDEX = 6;
    /**
     * 信息级别
     */
    static final int MSGLEVEL_INDEX = 7;
    /**
     * 日志信息
     */
    static final int MSG_INDEX = 8;
    /**
     * 创建时间
     */
    static final int CREATEDATE_INDEX = 9;

    static final StackTraceElement EMPTY_CALLER_DATA = CallerData.naInstance();

    static {
        // PreparedStatement.getGeneratedKeys() method was added in JDK 1.4
        Method getGeneratedKeysMethod;
        try {
            // the
            getGeneratedKeysMethod = PreparedStatement.class.getMethod("getGeneratedKeys");
        } catch (Exception ex) {
            getGeneratedKeysMethod = null;
        }
        GET_GENERATED_KEYS_METHOD = getGeneratedKeysMethod;
    }

    @Override
    public void start() {
        insertSql = SqlBuilder.buildInsertSql();
        super.start();
    }

    @Override
    protected Method getGeneratedKeysMethod() {
        return GET_GENERATED_KEYS_METHOD;
    }

    @Override
    protected String getInsertSQL() {
        return insertSql;
    }

    @Override
    protected void subAppend(ILoggingEvent event, Connection connection, PreparedStatement insertStatement) throws Throwable {
        bindLoggingMyInfoWithPreparedStatement(insertStatement);
        bindLoggingEventWithInsertStatement(insertStatement, event);
        // This is expensive... should we do it every time?
        bindCallerDataWithPreparedStatement(insertStatement, event.getCallerData());
        int updateCount = insertStatement.executeUpdate();
        if (updateCount != 1) {
            addWarn("Failed to insert loggingEvent");
        }
    }

    @Override
    protected void secondarySubAppend(ILoggingEvent eventObject, Connection connection, long eventId) throws Throwable {
        Map<String, String> mergedMap = mergePropertyMaps(eventObject);
        insertProperties(mergedMap, connection, eventId);

        if (eventObject.getThrowableProxy() != null) {
            insertThrowable(eventObject.getThrowableProxy(), connection, eventId);
        }
    }

    Map<String, String> mergePropertyMaps(ILoggingEvent event) {
        Map<String, String> mergedMap = new HashMap<>(16);
        // we add the context properties first, then the event properties, since
        // we consider that event-specific properties should have priority over
        // context-wide properties.
        Map<String, String> loggerContextMap = event.getLoggerContextVO().getPropertyMap();
        Map<String, String> mdcMap = event.getMDCPropertyMap();
        if (loggerContextMap != null) {
            mergedMap.putAll(loggerContextMap);
        }
        if (mdcMap != null) {
            mergedMap.putAll(mdcMap);
        }
        return mergedMap;
    }

    protected void insertProperties(Map<String, String> mergedMap, Connection connection, long eventId) throws SQLException {
        Set<String> propertiesKeys = mergedMap.keySet();
        if (propertiesKeys.size() > 0) {
            PreparedStatement insertPropertiesStatement = null;
            try {
                insertPropertiesStatement = connection.prepareStatement(insertPropertiesSql);

                for (String key : propertiesKeys) {
                    String value = mergedMap.get(key);

                    insertPropertiesStatement.setLong(1, eventId);
                    insertPropertiesStatement.setString(2, key);
                    insertPropertiesStatement.setString(3, value);

                    if (cnxSupportsBatchUpdates) {
                        insertPropertiesStatement.addBatch();
                    } else {
                        insertPropertiesStatement.execute();
                    }
                }

                if (cnxSupportsBatchUpdates) {
                    insertPropertiesStatement.executeBatch();
                }
            } finally {
                closeStatement(insertPropertiesStatement);
            }
        }
    }

    protected void insertThrowable(IThrowableProxy tp, Connection connection, long eventId) throws SQLException {

        PreparedStatement exceptionStatement = null;
        try {
            exceptionStatement = connection.prepareStatement(insertExceptionSql);

            short baseIndex = 0;
            while (tp != null) {
                baseIndex = buildExceptionStatement(tp, baseIndex, exceptionStatement, eventId);
                tp = tp.getCause();
            }

            if (cnxSupportsBatchUpdates) {
                exceptionStatement.executeBatch();
            }
        } finally {
            closeStatement(exceptionStatement);
        }

    }

    void bindLoggingMyInfoWithPreparedStatement(PreparedStatement stmt)throws SQLException{
        String appPort = "8040";
        stmt.setString(APP_INDEX, appPort);
        Date day=new Date();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        stmt.setString(CREATEDATE_INDEX,df.format(day));
    }

    void bindLoggingEventWithInsertStatement(PreparedStatement stmt, ILoggingEvent event) throws SQLException {
        stmt.setString(UUID_INDEX, String.valueOf(UUID.randomUUID()));
        stmt.setString(MSG_INDEX, event.getFormattedMessage());
        stmt.setString(MSGLEVEL_INDEX, event.getLevel().toString());
        stmt.setString(THREADNAME_INDEX, event.getThreadName());
    }

    void bindCallerDataWithPreparedStatement(PreparedStatement stmt, StackTraceElement[] callerDataArray) throws SQLException {

        StackTraceElement caller = extractFirstCaller(callerDataArray);

        stmt.setString(CLASS_INDEX, caller.getFileName());
        stmt.setString(CLASSPATH_INDEX, caller.getClassName());
        stmt.setString(METHOD_INDEX, caller.getMethodName());
    }

    private StackTraceElement extractFirstCaller(StackTraceElement[] callerDataArray) {
        StackTraceElement caller = EMPTY_CALLER_DATA;
        if (hasAtLeastOneNonNullElement(callerDataArray)) {
            caller = callerDataArray[0];
        }
        return caller;
    }

    private boolean hasAtLeastOneNonNullElement(StackTraceElement[] callerDataArray) {
        return callerDataArray != null && callerDataArray.length > 0 && callerDataArray[0] != null;
    }

    @Override
    public void append(ILoggingEvent eventObject) {
        Connection connection = null;
        try {
            connection = connectionSource.getConnection();
            connection.setAutoCommit(false);
            PreparedStatement insertStatement;
            insertStatement = connection.prepareStatement(getInsertSQL());
            // inserting an event and getting the result must be exclusive
            synchronized (this) {
                subAppend(eventObject, connection, insertStatement);
            }

            // we no longer need the insertStatement
            if (insertStatement != null) {
                insertStatement.close();
            }
            connection.commit();
        } catch (Throwable sqle) {
            addError("problem appending event", sqle);
        } finally {
            DBHelper.closeConnection(connection);
        }
    }

    short buildExceptionStatement(IThrowableProxy tp, short baseIndex, PreparedStatement insertExceptionStatement, long eventId) throws SQLException {

        StringBuilder buf = new StringBuilder();
        ThrowableProxyUtil.subjoinFirstLine(buf, tp);
        updateExceptionStatement(insertExceptionStatement, buf.toString(), baseIndex++, eventId);

        int commonFrames = tp.getCommonFrames();
        StackTraceElementProxy[] stepArray = tp.getStackTraceElementProxyArray();
        for (int i = 0; i < stepArray.length - commonFrames; i++) {
            StringBuilder sb = new StringBuilder();
            sb.append(CoreConstants.TAB);
            ThrowableProxyUtil.subjoinSTEP(sb, stepArray[i]);
            updateExceptionStatement(insertExceptionStatement, sb.toString(), baseIndex++, eventId);
        }

        if (commonFrames > 0) {
            updateExceptionStatement(insertExceptionStatement, CoreConstants.TAB + "... " + commonFrames + " common frames omitted", baseIndex++, eventId);
        }

        return baseIndex;
    }

    /**
     * Add an exception statement either as a batch or execute immediately if
     * batch updates are not supported.
     */
    void updateExceptionStatement(PreparedStatement exceptionStatement, String txt, short i, long eventId) throws SQLException {
        exceptionStatement.setLong(1, eventId);
        exceptionStatement.setShort(2, i);
        exceptionStatement.setString(3, txt);
        if (cnxSupportsBatchUpdates) {
            exceptionStatement.addBatch();
        } else {
            exceptionStatement.execute();
        }
    }
}
