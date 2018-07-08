/*
 * Copyright 2006 The Apache Software Foundation.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package struts;

import java.util.Date;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.validator.annotations.Validation;
import com.opensymphony.xwork2.validator.annotations.RequiredStringValidator;
import com.opensymphony.xwork2.validator.annotations.RequiredFieldValidator;
import com.opensymphony.xwork2.conversion.annotations.Conversion;
import com.opensymphony.xwork2.conversion.annotations.TypeConversion;

@Validation()
@Conversion()
public class LoginAction extends ActionSupport {

    private String name;
    private Date birthday;
    private String password;

    @RequiredStringValidator(message = "Please enter your name.")
    public void setName(String name) { this.name = name; }
    public String getName() { return name; }

    @TypeConversion(converter = "struts.DateConverter")
    @RequiredFieldValidator(message = "Please enter your birthday.")
    public void setBirthday(Date birthday) { this.birthday = birthday; }
    public Date getBirthday() { return birthday; }

    @RequiredStringValidator(message = "Please enter your password.")
    public void setPassword(String password) { this.password = password; }
    public String getPassword() { return password; }

    public String execute() throws Exception {
        return SUCCESS;
    }
}
