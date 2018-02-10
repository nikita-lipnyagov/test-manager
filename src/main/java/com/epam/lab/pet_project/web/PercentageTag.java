package com.epam.lab.pet_project.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;

public class PercentageTag extends TagSupport {
    private static final Logger LOG = LoggerFactory.getLogger(PercentageTag.class);
    private String mark;

    @Override
    public int doStartTag() throws JspException {
        try {
            pageContext.getOut().write(mark + " %");
            LOG.debug("new mark field was created");
        } catch (IOException e) {
            LOG.error(e.getMessage());
            throw new JspException(e.getMessage());
        }
        return SKIP_BODY;
    }

    @Override
    public void release() {
        super.release();
        this.mark = null;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String name) {
        this.mark = name;
    }
}