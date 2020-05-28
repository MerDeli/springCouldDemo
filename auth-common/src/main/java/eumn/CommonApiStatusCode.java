package eumn;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
//@Deprecated
public enum CommonApiStatusCode {
    NOT_EXIST(100001, "不存在"),
    ERROR(500, "内部错误"),
    SUCCESS(200, "成功");
    private int code;
    private String text;

    public Integer getCode(){
        return code;
    }

    public String getText(){
        return text;
    }
}
