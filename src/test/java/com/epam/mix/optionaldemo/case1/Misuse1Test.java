/*
 * Copyright 2021 Mikhail Podolskiy
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
package com.epam.mix.optionaldemo.case1;

import org.junit.Test;

import java.util.Optional;

@SuppressWarnings("ALL")
public class Misuse1Test {

    private StringService stringService = new StringService();

    private void misuse() {
        Optional<String> strOpt = stringService.getString();

        if (strOpt.isPresent()) {
            System.out.println(strOpt.get());
        }
    }

    private Optional<String> misuse2() {
        if (stringService.getString().isPresent()) {
            return Optional.ofNullable(stringService.getString().get().concat(" concat"));
        } else {
            return Optional.empty();
        }
    }

    private void useProperly() {
        Optional<String> str = stringService.getString();
        str.ifPresent(System.out::println);
    }

    @Test
    public void test_case1_misuse() {
        // WHEN
        misuse();
    }
}
