package com.sistema.apicr7imports.services;

import com.sistema.apicr7imports.data.dto.response.AcessResponse;

public interface IAcessService {

	AcessResponse getAcess(String username, String password);
}
