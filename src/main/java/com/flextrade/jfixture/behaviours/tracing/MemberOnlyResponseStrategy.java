package com.flextrade.jfixture.behaviours.tracing;

import java.io.IOException;
import java.lang.reflect.Member;

public class MemberOnlyResponseStrategy implements TracingStrategy {

    @Override
    public void writeRequest(Appendable appendable, Object request) throws IOException {
        // Do nothing
    }

    @Override
    public void writeCreated(Appendable appendable, Object request, Object specimen) throws IOException {
        if (!(request instanceof Member)) return;

        Member member = (Member) request;
        appendable.append(String.format("%s = %s", member.getName(), specimen));
        appendable.append("\n");
    }

    @Override
    public void writeError(Appendable appendable, Exception exception) throws IOException {
        // Do nothing
    }
}
