package com.sb.jwt.model;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sb.jwt.util.Constants;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class JwtResponseDto {
	private String jwtToken;
	private String error;
	@JsonFormat(pattern = Constants.API_DATE_TIME_FORMAT)
	private Date expiryDate;
	@JsonFormat(pattern = Constants.API_DATE_TIME_FORMAT)
	private Date generateDate;
}