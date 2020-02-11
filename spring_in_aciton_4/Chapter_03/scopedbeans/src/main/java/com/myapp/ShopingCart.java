package com.myapp;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

@Component
@Scope(value = WebApplicationContext.SCOP_SESSION, proxyMode = ScopedProxyMode.INTERFACES)
public class ShopingCart {
}
