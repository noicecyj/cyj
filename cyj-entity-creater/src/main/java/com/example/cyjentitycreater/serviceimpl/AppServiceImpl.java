package com.example.cyjentitycreater.serviceimpl;

import com.example.cyjcommon.utils.CommonUtils;
import com.example.cyjentitycreater.api.DictionaryApiService;
import com.example.cyjentitycreater.entity.AppServicePO;
import com.example.cyjentitycreater.entity.dto.DictionaryDTO;
import com.example.cyjentitycreater.utils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import static com.example.cyjentitycreater.utils.BeanUtils.AppFileName;
import static com.example.cyjentitycreater.utils.BeanUtils.testAppFileName;

/**
 * @author 曹元杰
 * @version 1.0
 * @date 2020/12/21 14:46
 */
@Component
public class AppServiceImpl extends BaseService {

    private DictionaryApiService dictionaryApiService;

    @Autowired
    public void setDictionaryApiService(DictionaryApiService dictionaryApiService) {
        this.dictionaryApiService = dictionaryApiService;
    }

    public void createJavaFile(AppServicePO po) throws IOException {
        String AppName = BeanUtils.captureName(BeanUtils.underline2Camel2(po.getName()));
        List<DictionaryDTO> pos = dictionaryApiService.findCatalogByValue("FILE_PATH");
        HashMap<String, DictionaryDTO> mapPo = CommonUtils.listToMap(pos, "dictionaryName");
        String AppFilePath = mapPo.get("servicePath").getDictionaryValue() + po.getName()
                + "\\src\\main\\java\\com\\example\\" + AppName;
        String[] AppResult = appGenerate(po);
        createJavaFile(AppFilePath, AppResult);
        String AppLogFilePath = mapPo.get("servicePath").getDictionaryValue() + po.getName()
                + "\\src\\main\\java\\com\\example\\" + AppName + "\\config";
        String[] AppLogResult = appLogGenerate(po);
        createJavaFile(AppLogFilePath, AppLogResult);
        String ymlFilePath = mapPo.get("servicePath").getDictionaryValue() + po.getName()
                + "\\src\\main\\resources";
        String[] ymlResult = ymlGenerate(po);
        createJavaFile(ymlFilePath, ymlResult);
        String xmlFilePath = mapPo.get("servicePath").getDictionaryValue() + po.getName()
                + "\\src\\main\\resources";
        String[] xmlResult = xmlGenerate(po);
        createJavaFile(xmlFilePath, xmlResult);
        String configFilePath = mapPo.get("servicePath").getDictionaryValue() + po.getName()
                + "\\src\\main\\resources";
        String[] configResult = configGenerate();
        createJavaFile(configFilePath, configResult);
        String testAppFilePath = mapPo.get("servicePath").getDictionaryValue() + po.getName()
                + "\\src\\test\\java\\com\\example\\" + AppName;
        String[] testAppResult = testAppGenerate(po);
        createJavaFile(testAppFilePath, testAppResult);
        String pomFilePath = mapPo.get("servicePath").getDictionaryValue() + po.getName();
        String[] pomResult = pomGenerate(po);
        createJavaFile(pomFilePath, pomResult);


    }

    public String[] appGenerate(AppServicePO po) {
        String AppName = BeanUtils.captureName(BeanUtils.underline2Camel2(po.getName()));
        StringBuilder sb = new StringBuilder();
        sb.append("package com.example.").append(AppName).append(";\r\n");
        sb.append("\r\n");
        sb.append("import org.springframework.boot.SpringApplication;\r\n");
        sb.append("import org.springframework.boot.autoconfigure.SpringBootApplication;\r\n");
        sb.append("import org.springframework.cloud.netflix.eureka.EnableEurekaClient;\r\n");
        sb.append("import org.springframework.cloud.openfeign.EnableFeignClients;\r\n");
        sb.append("\r\n");
        generateAuthor(sb);
        sb.append("@SpringBootApplication\r\n");
        sb.append("@EnableEurekaClient\r\n");
        sb.append("@EnableFeignClients\r\n");
        sb.append("public class ").append(AppName).append("Application {\r\n");
        sb.append("\r\n");
        sb.append("    public static void main(String[] args) {\r\n");
        sb.append("        SpringApplication.run(").append(AppName).append("Application.class, args);\r\n");
        sb.append("    }\r\n");
        sb.append("\r\n");
        sb.append("}\r\n");
        String serviceFileData = sb.toString();
        return new String[]{serviceFileData, AppFileName(po)};
    }

    public String[] appLogGenerate(AppServicePO po) {
        String AppName = BeanUtils.captureName(BeanUtils.underline2Camel2(po.getName()));
        String serviceFileData = "package com.example." + AppName + ".config;\r\n" +
                "\r\n" +
                "import ch.qos.logback.classic.spi.*;\r\n" +
                "import ch.qos.logback.core.CoreConstants;\r\n" +
                "import ch.qos.logback.core.db.DBAppenderBase;\r\n" +
                "import ch.qos.logback.core.db.DBHelper;\r\n" +
                "import com.example.cyjcommon.config.SqlBuilder;\r\n" +
                "\r\n" +
                "import java.lang.reflect.Method;\r\n" +
                "import java.sql.Connection;\r\n" +
                "import java.sql.PreparedStatement;\r\n" +
                "import java.sql.SQLException;\r\n" +
                "import java.text.SimpleDateFormat;\r\n" +
                "import java.util.*;\r\n" +
                "\r\n" +
                "import static ch.qos.logback.core.db.DBHelper.closeStatement;\r\n" +
                "\r\n" +
                "/**\r\n" +
                " * @author 曹元杰\r\n" +
                " * @version 1.0\r\n" +
                " * @date 2020/1/21 14:46\r\n" +
                " */\r\n" +
                "public class ServerLogAppender extends DBAppenderBase<ILoggingEvent> {\r\n" +
                "\r\n" +
                "    protected String insertPropertiesSql;\r\n" +
                "    protected String insertExceptionSql;\r\n" +
                "    protected static final Method GET_GENERATED_KEYS_METHOD;\r\n" +
                "\r\n" +
                "    protected String insertSql;\r\n" +
                "    /**\r\n" +
                "     * id\r\n" +
                "     */\r\n" +
                "    static final int UUID_INDEX = 1;\r\n" +
                "    /**\r\n" +
                "     * 应用的端口\r\n" +
                "     */\r\n" +
                "    static final int APP_INDEX = 2;\r\n" +
                "    /**\r\n" +
                "     * 类名\r\n" +
                "     */\r\n" +
                "    static final int CLASS_INDEX = 3;\r\n" +
                "    /**\r\n" +
                "     * 路径\r\n" +
                "     */\r\n" +
                "    static final int CLASSPATH_INDEX = 4;\r\n" +
                "    /**\r\n" +
                "     * 方法名\r\n" +
                "     */\r\n" +
                "    static final int METHOD_INDEX = 5;\r\n" +
                "    /**\r\n" +
                "     * 线程名\r\n" +
                "     */\r\n" +
                "    static final int THREADNAME_INDEX = 6;\r\n" +
                "    /**\r\n" +
                "     * 信息级别\r\n" +
                "     */\r\n" +
                "    static final int MSGLEVEL_INDEX = 7;\r\n" +
                "    /**\r\n" +
                "     * 日志信息\r\n" +
                "     */\r\n" +
                "    static final int MSG_INDEX = 8;\r\n" +
                "    /**\r\n" +
                "     * 创建时间\r\n" +
                "     */\r\n" +
                "    static final int CREATEDATE_INDEX = 9;\r\n" +
                "\r\n" +
                "    static final StackTraceElement EMPTY_CALLER_DATA = CallerData.naInstance();\r\n" +
                "\r\n" +
                "    static {\r\n" +
                "        // PreparedStatement.getGeneratedKeys() method was added in JDK 1.4\r\n" +
                "        Method getGeneratedKeysMethod;\r\n" +
                "        try {\r\n" +
                "            // the\r\n" +
                "            getGeneratedKeysMethod = PreparedStatement.class.getMethod(\"getGeneratedKeys\");\r\n" +
                "        } catch (Exception ex) {\r\n" +
                "            getGeneratedKeysMethod = null;\r\n" +
                "        }\r\n" +
                "        GET_GENERATED_KEYS_METHOD = getGeneratedKeysMethod;\r\n" +
                "    }\r\n" +
                "\r\n" +
                "    @Override\r\n" +
                "    public void start() {\r\n" +
                "        insertSql = SqlBuilder.buildInsertSql();\r\n" +
                "        super.start();\r\n" +
                "    }\r\n" +
                "\r\n" +
                "    @Override\r\n" +
                "    protected Method getGeneratedKeysMethod() {\r\n" +
                "        return GET_GENERATED_KEYS_METHOD;\r\n" +
                "    }\r\n" +
                "\r\n" +
                "    @Override\r\n" +
                "    protected String getInsertSQL() {\r\n" +
                "        return insertSql;\r\n" +
                "    }\r\n" +
                "\r\n" +
                "    @Override\r\n" +
                "    protected void subAppend(ILoggingEvent event, Connection connection, PreparedStatement insertStatement) throws Throwable {\r\n" +
                "        bindLoggingMyInfoWithPreparedStatement(insertStatement);\r\n" +
                "        bindLoggingEventWithInsertStatement(insertStatement, event);\r\n" +
                "        // This is expensive... should we do it every time?\r\n" +
                "        bindCallerDataWithPreparedStatement(insertStatement, event.getCallerData());\r\n" +
                "        int updateCount = insertStatement.executeUpdate();\r\n" +
                "        if (updateCount != 1) {\r\n" +
                "            addWarn(\"Failed to insert loggingEvent\");\r\n" +
                "        }\r\n" +
                "    }\r\n" +
                "\r\n" +
                "    @Override\r\n" +
                "    protected void secondarySubAppend(ILoggingEvent eventObject, Connection connection, long eventId) throws Throwable {\r\n" +
                "        Map<String, String> mergedMap = mergePropertyMaps(eventObject);\r\n" +
                "        insertProperties(mergedMap, connection, eventId);\r\n" +
                "        if (eventObject.getThrowableProxy() != null) {\r\n" +
                "            insertThrowable(eventObject.getThrowableProxy(), connection, eventId);\r\n" +
                "        }\r\n" +
                "    }\r\n" +
                "\r\n" +
                "    Map<String, String> mergePropertyMaps(ILoggingEvent event) {\r\n" +
                "        Map<String, String> mergedMap = new HashMap<>(16);\r\n" +
                "        // we add the context properties first, then the event properties, since\r\n" +
                "        // we consider that event-specific properties should have priority over\r\n" +
                "        // context-wide properties.\r\n" +
                "        Map<String, String> loggerContextMap = event.getLoggerContextVO().getPropertyMap();\r\n" +
                "        Map<String, String> mdcMap = event.getMDCPropertyMap();\r\n" +
                "        if (loggerContextMap != null) {\r\n" +
                "            mergedMap.putAll(loggerContextMap);\r\n" +
                "        }\r\n" +
                "        if (mdcMap != null) {\r\n" +
                "            mergedMap.putAll(mdcMap);\r\n" +
                "        }\r\n" +
                "        return mergedMap;\r\n" +
                "    }\r\n" +
                "\r\n" +
                "    protected void insertProperties(Map<String, String> mergedMap, Connection connection, long eventId) throws SQLException {\r\n" +
                "        Set<String> propertiesKeys = mergedMap.keySet();\r\n" +
                "        if (propertiesKeys.size() > 0) {\r\n" +
                "            PreparedStatement insertPropertiesStatement = null;\r\n" +
                "            try {\r\n" +
                "                insertPropertiesStatement = connection.prepareStatement(insertPropertiesSql);\r\n" +
                "                for (String key : propertiesKeys) {\r\n" +
                "                    String value = mergedMap.get(key);\r\n" +
                "                    insertPropertiesStatement.setLong(1, eventId);\r\n" +
                "                    insertPropertiesStatement.setString(2, key);\r\n" +
                "                    insertPropertiesStatement.setString(3, value);\r\n" +
                "                    if (cnxSupportsBatchUpdates) {\r\n" +
                "                        insertPropertiesStatement.addBatch();\r\n" +
                "                    } else {\r\n" +
                "                        insertPropertiesStatement.execute();\r\n" +
                "                    }\r\n" +
                "                }\r\n" +
                "                if (cnxSupportsBatchUpdates) {\r\n" +
                "                    insertPropertiesStatement.executeBatch();\r\n" +
                "                }\r\n" +
                "            } finally {\r\n" +
                "                closeStatement(insertPropertiesStatement);\r\n" +
                "            }\r\n" +
                "        }\r\n" +
                "    }\r\n" +
                "\r\n" +
                "    protected void insertThrowable(IThrowableProxy tp, Connection connection, long eventId) throws SQLException {\r\n" +
                "        PreparedStatement exceptionStatement = null;\r\n" +
                "        try {\r\n" +
                "            exceptionStatement = connection.prepareStatement(insertExceptionSql);\r\n" +
                "            short baseIndex = 0;\r\n" +
                "            while (tp != null) {\r\n" +
                "                baseIndex = buildExceptionStatement(tp, baseIndex, exceptionStatement, eventId);\r\n" +
                "                tp = tp.getCause();\r\n" +
                "            }\r\n" +
                "            if (cnxSupportsBatchUpdates) {\r\n" +
                "                exceptionStatement.executeBatch();\r\n" +
                "            }\r\n" +
                "        } finally {\r\n" +
                "            closeStatement(exceptionStatement);\r\n" +
                "        }\r\n" +
                "    }\r\n" +
                "\r\n" +
                "    void bindLoggingMyInfoWithPreparedStatement(PreparedStatement stmt) throws SQLException {\r\n" +
                "        String appPort = \"" + po.getAppPort() + "\";\r\n" +
                "        stmt.setString(APP_INDEX, appPort);\r\n" +
                "        Date day = new Date();\r\n" +
                "        SimpleDateFormat df = new SimpleDateFormat(\"yyyy-MM-dd HH:mm:ss\");\r\n" +
                "        stmt.setString(CREATEDATE_INDEX, df.format(day));\r\n" +
                "    }\r\n" +
                "\r\n" +
                "    void bindLoggingEventWithInsertStatement(PreparedStatement stmt, ILoggingEvent event) throws SQLException {\r\n" +
                "        stmt.setString(UUID_INDEX, String.valueOf(UUID.randomUUID()));\r\n" +
                "        stmt.setString(MSG_INDEX, event.getFormattedMessage());\r\n" +
                "        stmt.setString(MSGLEVEL_INDEX, event.getLevel().toString());\r\n" +
                "        stmt.setString(THREADNAME_INDEX, event.getThreadName());\r\n" +
                "    }\r\n" +
                "\r\n" +
                "    void bindCallerDataWithPreparedStatement(PreparedStatement stmt, StackTraceElement[] callerDataArray) throws SQLException {\r\n" +
                "        StackTraceElement caller = extractFirstCaller(callerDataArray);\r\n" +
                "        stmt.setString(CLASS_INDEX, caller.getFileName());\r\n" +
                "        stmt.setString(CLASSPATH_INDEX, caller.getClassName());\r\n" +
                "        stmt.setString(METHOD_INDEX, caller.getMethodName());\r\n" +
                "    }\r\n" +
                "\r\n" +
                "    private StackTraceElement extractFirstCaller(StackTraceElement[] callerDataArray) {\r\n" +
                "        StackTraceElement caller = EMPTY_CALLER_DATA;\r\n" +
                "        if (hasAtLeastOneNonNullElement(callerDataArray)) {\r\n" +
                "            caller = callerDataArray[0];\r\n" +
                "        }\r\n" +
                "        return caller;\r\n" +
                "    }\r\n" +
                "\r\n" +
                "    private boolean hasAtLeastOneNonNullElement(StackTraceElement[] callerDataArray) {\r\n" +
                "        return callerDataArray != null && callerDataArray.length > 0 && callerDataArray[0] != null;\r\n" +
                "    }\r\n" +
                "\r\n" +
                "    @Override\r\n" +
                "    public void append(ILoggingEvent eventObject) {\r\n" +
                "        Connection connection = null;\r\n" +
                "        try {\r\n" +
                "            connection = connectionSource.getConnection();\r\n" +
                "            connection.setAutoCommit(false);\r\n" +
                "            PreparedStatement insertStatement;\r\n" +
                "            insertStatement = connection.prepareStatement(getInsertSQL());\r\n" +
                "            // inserting an event and getting the result must be exclusive\r\n" +
                "            synchronized (this) {\r\n" +
                "                subAppend(eventObject, connection, insertStatement);\r\n" +
                "            }\r\n" +
                "            // we no longer need the insertStatement\r\n" +
                "            if (insertStatement != null) {\r\n" +
                "                insertStatement.close();\r\n" +
                "            }\r\n" +
                "            connection.commit();\r\n" +
                "        } catch (Throwable sqle) {\r\n" +
                "            addError(\"problem appending event\", sqle);\r\n" +
                "        } finally {\r\n" +
                "            DBHelper.closeConnection(connection);\r\n" +
                "        }\r\n" +
                "    }\r\n" +
                "\r\n" +
                "    short buildExceptionStatement(IThrowableProxy tp, short baseIndex, PreparedStatement insertExceptionStatement, long eventId) throws SQLException {\r\n" +
                "        StringBuilder buf = new StringBuilder();\r\n" +
                "        ThrowableProxyUtil.subjoinFirstLine(buf, tp);\r\n" +
                "        updateExceptionStatement(insertExceptionStatement, buf.toString(), baseIndex++, eventId);\r\n" +
                "        int commonFrames = tp.getCommonFrames();\r\n" +
                "        StackTraceElementProxy[] stepArray = tp.getStackTraceElementProxyArray();\r\n" +
                "        for (int i = 0; i < stepArray.length - commonFrames; i++) {\r\n" +
                "            StringBuilder sb = new StringBuilder();\r\n" +
                "            sb.append(CoreConstants.TAB);\r\n" +
                "            ThrowableProxyUtil.subjoinSTEP(sb, stepArray[i]);\r\n" +
                "            updateExceptionStatement(insertExceptionStatement, sb.toString(), baseIndex++, eventId);\r\n" +
                "        }\r\n" +
                "        if (commonFrames > 0) {\r\n" +
                "            updateExceptionStatement(insertExceptionStatement, CoreConstants.TAB + \"... \" + commonFrames + \" common frames omitted\", baseIndex++, eventId);\r\n" +
                "        }\r\n" +
                "        return baseIndex;\r\n" +
                "    }\r\n" +
                "\r\n" +
                "    void updateExceptionStatement(PreparedStatement exceptionStatement, String txt, short i, long eventId) throws SQLException {\r\n" +
                "        exceptionStatement.setLong(1, eventId);\r\n" +
                "        exceptionStatement.setShort(2, i);\r\n" +
                "        exceptionStatement.setString(3, txt);\r\n" +
                "        if (cnxSupportsBatchUpdates) {\r\n" +
                "            exceptionStatement.addBatch();\r\n" +
                "        } else {\r\n" +
                "            exceptionStatement.execute();\r\n" +
                "        }\r\n" +
                "    }\r\n" +
                "}\r\n";
        return new String[]{serviceFileData, "ServerLogAppender.java"};
    }

    public String[] ymlGenerate(AppServicePO po) {
        String serviceFileData = "server:\r\n" +
                "  port: " + po.getAppPort() + "\r\n" +
                "eureka:\r\n" +
                "  client:\r\n" +
                "    serviceUrl:\r\n" +
                "      defaultZone: http://localhost:8761/eureka\r\n" +
                "    fetch-registry: false\r\n" +
                "  instance:\r\n" +
                "    prefer-ip-address: true\r\n" +
                "spring:\r\n" +
                "  application:\r\n" +
                "    name: " + po.getName() + "\r\n" +
                "  jpa:\r\n" +
                "    generate-ddl: false\r\n" +
                "    show-sql: true\r\n" +
                "    hibernate:\r\n" +
                "      ddl-auto: update\r\n" +
                "  datasource:\r\n" +
                "    platform: mysql\r\n" +
                "    driver-class-name: com.mysql.cj.jdbc.Driver\r\n" +
                "    url: jdbc:mysql://localhost:3306/data_user?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC\r\n" +
                "    username: root\r\n" +
                "    password: 68762306\r\n" +
                "#feign的配置，连接超时及读取超时配置\r\n" +
                "feign:\r\n" +
                "  client:\r\n" +
                "    config:\r\n" +
                "      default:\r\n" +
                "        connectTimeout: 60000\r\n" +
                "        readTimeout: 60000\r\n" +
                "        loggerLevel: basic\r\n" +
                "  hystrix:\r\n" +
                "    enabled: false\r\n";
        return new String[]{serviceFileData, "application.yml"};
    }

    public String[] xmlGenerate(AppServicePO po) {
        String AppName = BeanUtils.captureName(BeanUtils.underline2Camel2(po.getName()));
        String serviceFileData = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\r\n" +
                "<!--该日志将日志级别不同的log信息保存到不同的文件中 -->\r\n" +
                "<configuration debug=\"false\">\r\n" +
                "    <!--定义日志文件的存储地址 勿在 LogBack 的配置中使用相对路径-->\r\n" +
                "    <property name=\"LOG_HOME\" value=\"/log\"/>\r\n" +
                "\r\n" +
                "    <!-- 控制台输出 -->\r\n" +
                "    <appender name=\"stdout\" class=\"ch.qos.logback.core.ConsoleAppender\">\r\n" +
                "        <encoder class=\"ch.qos.logback.classic.encoder.PatternLayoutEncoder\">\r\n" +
                "            <!--格式化输出：%d表示日期，%thread表示线程名，%-5level：级别从左显示5个字符宽度%msg：日志消息，%n是换行符-->\r\n" +
                "            <pattern>%d{yyyy-MM-dd HH:mm:ss.SSS} [%thread] %-5level %logger{50} - %msg%n</pattern>\r\n" +
                "        </encoder>\r\n" +
                "    </appender>\r\n" +
                "\r\n" +
                "    <!--连接数据库配置-->\r\n" +
                "    <appender name=\"db_classic_mysql_pool\" class=\"com.example." + AppName + ".config.ServerLogAppender\">\r\n" +
                "        <connectionSource class=\"ch.qos.logback.core.db.DataSourceConnectionSource\">\r\n" +
                "            <dataSource class=\"org.apache.commons.dbcp2.BasicDataSource\">\r\n" +
                "                <driverClassName>com.mysql.cj.jdbc.Driver</driverClassName>\r\n" +
                "                <url>jdbc:mysql://localhost:3306/data_user?useSSL=false&amp;allowPublicKeyRetrieval=true&amp;serverTimezone=UTC\r\n" +
                "                </url>\r\n" +
                "                <username>root</username>\r\n" +
                "                <password>68762306</password>\r\n" +
                "            </dataSource>\r\n" +
                "        </connectionSource>\r\n" +
                "    </appender>\r\n" +
                "\r\n" +
                "    <!-- 日志输出级别 -->\r\n" +
                "    <root level=\"INFO\">\r\n" +
                "        <appender-ref ref=\"stdout\"/>\r\n" +
                "        <appender-ref ref=\"db_classic_mysql_pool\"/>\r\n" +
                "    </root>\r\n" +
                "\r\n" +
                "</configuration>\r\n";
        return new String[]{serviceFileData, "logback-spring.xml"};
    }

    public String[] configGenerate() {
        String serviceFileData = "";
        return new String[]{serviceFileData, "config.properties"};
    }

    public String[] testAppGenerate(AppServicePO po) {
        String AppName = BeanUtils.captureName(BeanUtils.underline2Camel2(po.getName()));
        StringBuilder sb = new StringBuilder();
        sb.append("package com.example.").append(AppName).append(";\r\n");
        sb.append("\r\n");
        sb.append("import org.junit.jupiter.api.Test;\r\n");
        sb.append("import org.springframework.boot.test.context.SpringBootTest;\r\n");
        sb.append("\r\n");
        generateAuthor(sb);
        sb.append("@SpringBootTest\r\n");
        sb.append("public class ").append(AppName).append("ApplicationTests {\r\n");
        sb.append("\r\n");
        sb.append("    @Test\r\n");
        sb.append("    void contextLoads() {\r\n");
        sb.append("    }\r\n");
        sb.append("\r\n");
        sb.append("}\r\n");
        String serviceFileData = sb.toString();
        return new String[]{serviceFileData, testAppFileName(po)};
    }

    public String[] pomGenerate(AppServicePO po) {
        String serviceFileData = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\r\n" +
                "<project xmlns=\"http://maven.apache.org/POM/4.0.0\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"\r\n" +
                "         xsi:schemaLocation=\"http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd\">\r\n" +
                "    <modelVersion>4.0.0</modelVersion>\r\n" +
                "\r\n" +
                "    <groupId>com.example</groupId>\r\n" +
                "    <artifactId>" + po.getName() + "</artifactId>\r\n" +
                "    <version>0.0.1-SNAPSHOT</version>\r\n" +
                "    <packaging>jar</packaging>\r\n" +
                "\r\n" +
                "    <name>" + po.getName() + "</name>\r\n" +
                "    <description>Demo project for Spring Boot</description>\r\n" +
                "\r\n" +
                "    <parent>\r\n" +
                "        <groupId>noice</groupId>\r\n" +
                "        <artifactId>cyj</artifactId>\r\n" +
                "        <version>1.0-SNAPSHOT</version>\r\n" +
                "    </parent>\r\n" +
                "\r\n" +
                "    <dependencyManagement>\r\n" +
                "        <dependencies>\r\n" +
                "            <dependency>\r\n" +
                "                <groupId>org.springframework.cloud</groupId>\r\n" +
                "                <artifactId>spring-cloud-dependencies</artifactId>\r\n" +
                "                <version>Hoxton.SR10</version>\r\n" +
                "                <type>pom</type>\r\n" +
                "                <scope>import</scope>\r\n" +
                "            </dependency>\r\n" +
                "        </dependencies>\r\n" +
                "    </dependencyManagement>\r\n" +
                "\r\n" +
                "    <properties>\r\n" +
                "        <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>\r\n" +
                "        <project.reporting.outputEncoding>UTF-8</project.reporting.outputEncoding>\r\n" +
                "        <java.version>1.8</java.version>\r\n" +
                "    </properties>\r\n" +
                "\r\n" +
                "    <dependencies>\r\n" +
                "        <dependency>\r\n" +
                "            <groupId>org.springframework.boot</groupId>\r\n" +
                "            <artifactId>spring-boot-starter-test</artifactId>\r\n" +
                "            <scope>test</scope>\r\n" +
                "        </dependency>\r\n" +
                "        <dependency>\r\n" +
                "            <groupId>org.springframework.boot</groupId>\r\n" +
                "            <artifactId>spring-boot-starter</artifactId>\r\n" +
                "        </dependency>\r\n" +
                "        <dependency>\r\n" +
                "            <groupId>org.springframework.cloud</groupId>\r\n" +
                "            <artifactId>spring-cloud-starter-openfeign</artifactId>\r\n" +
                "        </dependency>\r\n" +
                "        <dependency>\r\n" +
                "            <groupId>org.springframework.cloud</groupId>\r\n" +
                "            <artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>\r\n" +
                "        </dependency>\r\n" +
                "        <dependency>\r\n" +
                "            <groupId>org.springframework.boot</groupId>\r\n" +
                "            <artifactId>spring-boot-starter-web</artifactId>\r\n" +
                "        </dependency>\r\n" +
                "        <dependency>\r\n" +
                "            <groupId>org.junit.jupiter</groupId>\r\n" +
                "            <artifactId>junit-jupiter-api</artifactId>\r\n" +
                "            <scope>test</scope>\r\n" +
                "        </dependency>\r\n" +
                "        <dependency>\r\n" +
                "            <groupId>org.springframework.boot</groupId>\r\n" +
                "            <artifactId>spring-boot-configuration-processor</artifactId>\r\n" +
                "            <optional>true</optional>\r\n" +
                "        </dependency>\r\n" +
                "        <dependency>\r\n" +
                "            <groupId>org.springframework.boot</groupId>\r\n" +
                "            <artifactId>spring-boot-starter-data-jpa</artifactId>\r\n" +
                "        </dependency>\r\n" +
                "        <dependency>\r\n" +
                "            <groupId>com.querydsl</groupId>\r\n" +
                "            <artifactId>querydsl-jpa</artifactId>\r\n" +
                "        </dependency>\r\n" +
                "        <dependency>\r\n" +
                "            <groupId>com.querydsl</groupId>\r\n" +
                "            <artifactId>querydsl-apt</artifactId>\r\n" +
                "        </dependency>\r\n" +
                "        <dependency>\r\n" +
                "            <groupId>mysql</groupId>\r\n" +
                "            <artifactId>mysql-connector-java</artifactId>\r\n" +
                "        </dependency>\r\n" +
                "        <dependency>\r\n" +
                "            <groupId>org.apache.commons</groupId>\r\n" +
                "            <artifactId>commons-dbcp2</artifactId>\r\n" +
                "            <version>2.7.0</version>\r\n" +
                "        </dependency>\r\n" +
                "        <dependency>\r\n" +
                "            <groupId>org.springdoc</groupId>\r\n" +
                "            <artifactId>springdoc-openapi-ui</artifactId>\r\n" +
                "            <version>1.4.0</version>\r\n" +
                "        </dependency>\r\n" +
                "        <dependency>\r\n" +
                "            <groupId>com.example</groupId>\r\n" +
                "            <artifactId>cyj-common</artifactId>\r\n" +
                "            <version>0.0.1-SNAPSHOT</version>\r\n" +
                "        </dependency>\r\n" +
                "    </dependencies>\r\n" +
                "\r\n" +
                "    <build>\r\n" +
                "        <plugins>\r\n" +
                "            <plugin>\r\n" +
                "                <groupId>org.springframework.boot</groupId>\r\n" +
                "                <artifactId>spring-boot-maven-plugin</artifactId>\r\n" +
                "            </plugin>\r\n" +
                "            <plugin>\r\n" +
                "                <groupId>com.mysema.maven</groupId>\r\n" +
                "                <artifactId>apt-maven-plugin</artifactId>\r\n" +
                "                <version>1.1.3</version>\r\n" +
                "                <executions>\r\n" +
                "                    <execution>\r\n" +
                "                        <goals>\r\n" +
                "                            <goal>process</goal>\r\n" +
                "                        </goals>\r\n" +
                "                        <configuration>\r\n" +
                "                            <outputDirectory>target/generated-sources/java</outputDirectory>\r\n" +
                "                            <processor>com.querydsl.apt.jpa.JPAAnnotationProcessor</processor>\r\n" +
                "                        </configuration>\r\n" +
                "                    </execution>\r\n" +
                "                </executions>\r\n" +
                "            </plugin>\r\n" +
                "        </plugins>\r\n" +
                "    </build>\r\n" +
                "\r\n" +
                "</project>\r\n";
        return new String[]{serviceFileData, "pom.xml"};
    }
}
