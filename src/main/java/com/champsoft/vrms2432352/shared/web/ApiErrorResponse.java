package com.champsoft.vrms2432352.shared.web;

import java.time.Instant;

public record ApiErrorResponse(
    Instant timestamp,
    int status,
    String error,
    String message,
    String path,
    Object details
) { }
