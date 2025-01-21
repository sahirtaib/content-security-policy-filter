# Content Security Policy Header Filter

A Java Servlet filter that applies [Content Security Policy (CSP)](https://content-security-policy.com) header to HTTP responses.

The filter can be used in your web application to improve security by controlling where resources can be loaded from.


## 1. Clone the Repository and Compile with Maven

To get started, clone this repository and compile the project using Maven:

```bash
git clone <this-repository-url>
cd content-security-policy-filter
mvn clean package
```

This will generate the `content-security-policy-filter.jar` file in the `target` directory.

## 2. Install the JAR and Modify `web.xml`

There are two options for using the `ContentSecurityPolicyHeaderFilter` in your Tomcat:

### Option 1: Install Globally for All Applications

To apply the `ContentSecurityPolicyHeaderFilter` globally across all web applications in Tomcat, follow these steps:

1. **Copy the JAR to Tomcat's `lib` directory:**

   Copy the `content-security-policy-filter.jar` file to the `lib` directory of your Tomcat installation:

   ```bash
   cp target/content-security-policy-filter.jar $TOMCAT_HOME/lib/
   ```

   This will make the filter available to all applications running on this Tomcat.

2. **Configure the Filter in `web.xml`:**

   Since the filter is now globally available, you can add it to any application’s `web.xml`. Here’s an example of how to configure it in the `web.xml` file for your web application:

   ```xml
   <filter>
       <filter-name>ContentSecurityPolicyHeaderFilter</filter-name>
       <filter-class>org.joget.ContentSecurityPolicyHeaderFilter</filter-class>
       <init-param>
           <param-name>value</param-name>
           <param-value>
               default-src 'self'; connect-src 'self' wss:; script-src 'self' 'unsafe-inline' 'unsafe-eval'; style-src 'self' 'unsafe-inline'; img-src 'self' data:; font-src 'self'; frame-src 'self'; object-src 'none'; child-src 'none';
           </param-value>
       </init-param>
   </filter>
   <filter-mapping>
       <filter-name>ContentSecurityPolicyHeaderFilter</filter-name>
       <url-pattern>/*</url-pattern>
       <async-supported>true</async-supported>
   </filter-mapping>
   ```

3. **Restart Tomcat:**

   Restart Tomcat for the changes to take effect.

### Option 2: Install for Joget only

To use the `ContentSecurityPolicyHeaderFilter` only for Joget, follow these steps:

1. **Copy the JAR to Joget's `WEB-INF/lib` Directory:**

   Place the `content-security-policy-filter.jar` file in the `WEB-INF/lib` directory:

   ```bash
   cp target/content-security-policy-filter.jar $TOMCAT_HOME/webapps/jw/WEB-INF/lib/
   ```

2. **Configure the Filter in the Joget's `web.xml`:**

   Add the following configuration to Joget's `web.xml` (located in `$TOMCAT_HOME/webapps/jw/WEB-INF/web.xml`):

   ```xml
   <filter>
       <filter-name>ContentSecurityPolicyHeaderFilter</filter-name>
       <filter-class>org.joget.ContentSecurityPolicyHeaderFilter</filter-class>
       <init-param>
           <param-name>value</param-name>
           <param-value>
               default-src 'self'; connect-src 'self' wss:; script-src 'self' 'unsafe-inline' 'unsafe-eval'; style-src 'self' 'unsafe-inline'; img-src 'self' data:; font-src 'self'; frame-src 'self'; object-src 'none'; child-src 'none';
           </param-value>
       </init-param>
   </filter>
   <filter-mapping>
       <filter-name>ContentSecurityPolicyHeaderFilter</filter-name>
       <url-pattern>/*</url-pattern>
       <async-supported>true</async-supported>
   </filter-mapping>
   ```

3. **Restart Joget:**

   After modifying the `web.xml`, restart Joget by stopping and starting it from the Tomcat Manager.

   Alternatively, you may also restart Joget by restarting Tomcat.

## 3. Verify the CSP Header

Once the filter is applied, you can verify that the `Content-Security-Policy` header is being set correctly by inspecting the HTTP response headers in your browser's developer tools or by using `curl`:

```bash
curl -I http://your-joget-server/jw
```

Look for the `Content-Security-Policy` header in the response.

## Licensing

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

        http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.

NOTE: This software may depend on other packages that may be licensed under different open source licenses.