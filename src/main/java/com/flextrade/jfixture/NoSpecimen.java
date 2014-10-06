package com.flextrade.jfixture;

import lombok.Data;

// Because {null} can be a valid specimen
// we need to use a special type to signal
// that the specimen builder can't deal
// with the request
@Data
public class NoSpecimen { }
