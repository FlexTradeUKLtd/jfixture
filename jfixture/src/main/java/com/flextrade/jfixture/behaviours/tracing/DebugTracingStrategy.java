package com.flextrade.jfixture.behaviours.tracing;

import com.flextrade.jfixture.utility.StringUtil;

import java.io.IOException;

public class DebugTracingStrategy implements TracingStrategy {

    private int depth;

    public void writeRequest(Appendable appendable, Object request) throws IOException {
        appendable.append(StringUtil.repeat("\t", depth++));
        appendable.append("Requested: ").append(request.toString());
        appendable.append("\n");
    }

    public void writeCreated(Appendable appendable, Object request, Object specimen) throws IOException {
        appendable.append(StringUtil.repeat("\t", --depth));

        if(specimen == null) {
            appendable.append("Created: ").append("null instance");
        } else {
            appendable.append("Created: ").append(specimen.toString());
        }

        appendable.append("\n");
    }

    @Override
    public void writeError(Appendable appendable, Exception exception) throws IOException {
        appendable.append(StringUtil.repeat("\t", depth));
        appendable.append("Exception thrown:");
        appendable.append("\n");
        appendable.append(expandedExceptionMessage(exception, new StringBuilder()));
        appendable.append("\n");
    }

    private static String expandedExceptionMessage(Throwable throwable, StringBuilder sb) {
        sb.append(throwable.toString());
        if(throwable.getCause() == null)
            return sb.toString();

        sb.append("\n");
        return expandedExceptionMessage(throwable.getCause(), sb);
    }
}
