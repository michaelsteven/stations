/**
 * Copyright (c)2018 - Michael S. Hepfer <michaelsteven@hepfer.org>
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

package com.iheartmedia.stations;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * The Application class
 * @author Michael_Hepfer
 *
 * NOTE: Explicitly specifying CompenentScan to override all in @SpringBootApplication for the unit test context to work
 */
@SpringBootApplication
@ComponentScan(basePackages = "com.iheartmedia.stations.service, com.iheartmedia.stations.controller")
@EnableSwagger2
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
	
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo()).select().paths(PathSelectors.any()).build();
    }

	
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder().title("iHeartMedia Stations API").description("API for the iHeartMedia Stations Microservice.").version("1.0").build();
    }
    

}
