package com.boseongcho.in4goback.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {

    /* 엔티티와 DTO 변환을 위한 modelMapper 빈 설정 */
    @Bean
    public ModelMapper modelMapper(){ return new ModelMapper(); }
}
/*
  *ModelMapper (api)
: DTO <-> Entity 객체로 변경해줌

 -사용
```java
*하나 바꿔줄 때

modelMapper.map(menuEntity, MenuDTO.class);
                entity객체,   DTO.class

*여러 개 (List)
menuList.stream().map(menu -> modelMapper.map(menu, MenuDTO.class)).collect(Collectors.toList());
* */