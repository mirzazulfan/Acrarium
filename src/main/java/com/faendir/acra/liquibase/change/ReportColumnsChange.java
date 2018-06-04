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

package com.faendir.acra.liquibase.change;

import com.faendir.acra.liquibase.LiquibaseChangePostProcessor;
import com.faendir.acra.model.Report;
import com.faendir.acra.service.data.DataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

/**
 * @author lukas
 * @since 01.06.18
 */
@Component
public class ReportColumnsChange extends LiquibaseChangePostProcessor {

    @NonNull private final DataService dataService;

    @Autowired
    public ReportColumnsChange(@NonNull @Lazy DataService dataService) {
        super("2018-06-01-add-report-columns");
        this.dataService = dataService;
    }

    @Override
    protected void afterChange() {
        dataService.transformAllReports(Report::initFields);
    }
}
