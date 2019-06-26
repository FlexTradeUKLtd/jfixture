package com.flextrade.jfixture.behaviours.tracing;

import java.io.IOException;

interface TracingStrategy {

    void writeRequest(Appendable appendable, Object request) throws IOException;

    void writeCreated(Appendable appendable, Object request, Object specimen) throws IOException;

    void writeError(Appendable appendable, Exception exception) throws IOException;
}
