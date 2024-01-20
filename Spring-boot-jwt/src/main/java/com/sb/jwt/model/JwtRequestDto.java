package com.sb.jwt.model;
import lombok.Data;

@Data
public class JwtRequestDto {
	private String username;
	private String secretKey;
}