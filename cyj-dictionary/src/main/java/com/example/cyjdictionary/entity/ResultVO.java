package com.example.cyjdictionary.entity;

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

    public static ResultVO failure(ResultCode resultCode) {
        ResultVO result = new ResultVO();
        result.setResultCode(resultCode);
        return result;
    }

    public static ResultVO failure(ResultCode resultCode, Object data) {
        ResultVO result = new ResultVO();
        result.setResultCode(resultCode);
        result.setData(data);
        return result;
    }

    public void setResultCode(ResultCode code) {
        this.code = code.code();
        this.msg = code.message();
    }
}
