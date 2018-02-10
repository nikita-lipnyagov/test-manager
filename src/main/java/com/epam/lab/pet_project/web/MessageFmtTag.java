package com.epam.lab.pet_project.web;

import com.epam.lab.pet_project.utils.CPControl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

public class MessageFmtTag extends TagSupport {
    private static final Logger LOG = LoggerFactory.getLogger(MessageFmtTag.class);
    private ResourceBundle.Control cpControl = new CPControl();
    private String key, language;

    @Override
    public int doStartTag() throws JspException {
        Locale locale = new Locale(language);

        ResourceBundle messages = ResourceBundle.getBundle("i18n.messages", locale, cpControl);
        try {
            String message = messages.getString(key);
            pageContext.getOut().write(message);
            LOG.debug("field was localized");
        } catch (IOException e) {
            LOG.error(e.getMessage());
            e.printStackTrace();
        }
        return SKIP_BODY;
    }

    @Override
    public void release() {
        super.release();
        this.key = null;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }
}
