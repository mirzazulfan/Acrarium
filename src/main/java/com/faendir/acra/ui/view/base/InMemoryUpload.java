/*
 * (C) Copyright 2018 Lukas Morawietz (https://github.com/F43nd1r)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.faendir.acra.ui.view.base;

import com.faendir.acra.i18n.HasI18n;
import com.vaadin.ui.Upload;
import org.vaadin.spring.i18n.I18N;
import org.vaadin.spring.i18n.support.Translatable;

import java.io.ByteArrayOutputStream;
import java.util.Locale;

/**
 * @author Lukas
 * @since 19.12.2017
 */
public class InMemoryUpload extends Upload implements Translatable, HasI18n {
    private final I18N i18n;
    private final String captionId;
    private final Object[] params;
    private final ByteArrayOutputStream outputStream;
    private boolean finished;

    public InMemoryUpload(I18N i18n, String captionId, Object... params) {
        super();
        this.i18n = i18n;
        this.captionId = captionId;
        this.params = params;
        outputStream = new ByteArrayOutputStream();
        finished = false;
        setReceiver((filename, mimeType) -> outputStream);
        addSucceededListener(event -> finished = true);
        addFailedListener(event -> finished = false);
        updateMessageStrings(i18n.getLocale());
    }

    public boolean isUploaded() {
        return finished;
    }

    public String getUploadedString() {
        return outputStream.toString();
    }

    @Override
    public void updateMessageStrings(Locale locale) {
        setCaption(i18n.get(captionId, locale, params));
    }

    @Override
    public I18N getI18n() {
        return i18n;
    }
}
