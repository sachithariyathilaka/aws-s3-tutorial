package com.bytegen.s3tutorial.resource;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Response resource of file controller.
 *
 * @author  Sachith Ariyathilaka
 * @version 1.0.0
 * @since   2024/03/17
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class APIResponse<T> {
    private int code;
    private String message;
    private T data;
}