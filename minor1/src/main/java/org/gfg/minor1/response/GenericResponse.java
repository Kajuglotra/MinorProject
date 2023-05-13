package org.gfg.minor1.response;

import lombok.*;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GenericResponse<T> {
    private Integer statusCode;

    private String message;

    private Integer code;

    private T data;

}
