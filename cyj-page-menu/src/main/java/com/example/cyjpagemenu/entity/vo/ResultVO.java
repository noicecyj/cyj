package com.example.cyjpagemenu.entity.vo;

import com.example.cyjpagemenu.entity.ResultCode;
import lombok.Data;

/**
 * @author 曹元杰
 * @version 1.0
 * @date 2020/7/07 14:46
 */

@Data
public class ResultVO {
    private static final long serialVersionUID = -3948389268046368059L;

    private Integer code;

    private String msg;

    private Object data;

    public ResultVO() {
    }

    public ResultVO(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public static ResultVO success() {
        ResultVO result = new ResultVO();
        result.setResultCode(ResultCode.SUCCESS);
        return result;
    }

    public static ResultVO success(Object data) {
        ResultVO result = new ResultVO();
        result.setResultCode(ResultCode.SUCCESS);
        result.setData(data);
        return result;
    }

    public static ResultVO failure() {
        ResultVO result = new ResultVO();
        result.setResultCode(ResultCode.FAILURE);
        return result;
    }

    public static ResultVO failure(Object data) {
        ResultVO result = new ResultVO();
        result.setResultCode(ResultCode.FAILURE);
        result.setData(data);
        return result;
    }

    public void setResultCode(ResultCode code) {
        this.code = code.code();
        this.msg = code.message();
    }
}
