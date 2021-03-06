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
package com.faendir.acra.ui.view.app.tabs;

import com.faendir.acra.i18n.Messages;
import com.faendir.acra.model.App;
import com.faendir.acra.model.Permission;
import com.faendir.acra.ui.annotation.RequiresAppPermission;
import com.faendir.acra.ui.view.app.tabs.panels.AdminPanel;
import com.faendir.acra.ui.view.base.layout.PanelFlexTab;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.ViewScope;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.vaadin.spring.i18n.I18N;

import java.util.List;

/**
 * @author Lukas
 * @since 19.05.2017
 */
@RequiresAppPermission(Permission.Level.ADMIN)
@SpringComponent
@ViewScope
public class AdminTab extends PanelFlexTab<App> implements AppTab {
    @NonNull private final I18N i18n;

    @Autowired
    public AdminTab(@NonNull List<AdminPanel> panels, @NonNull I18N i18n) {
        super(panels);
        this.i18n = i18n;
    }

    @Override
    public String getCaption() {
        return i18n.get(Messages.ADMIN);
    }

    @Override
    public String getId() {
        return "admin";
    }

    @Override
    public int getOrder() {
        return 4;
    }
}
