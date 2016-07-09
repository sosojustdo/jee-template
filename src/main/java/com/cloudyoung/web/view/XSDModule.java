package com.cloudyoung.web.view;

import org.codehaus.jackson.Version;

import org.codehaus.jackson.map.AnnotationIntrospector;

import org.codehaus.jackson.map.module.SimpleModule;

import org.codehaus.jackson.xc.JaxbAnnotationIntrospector;

public class XSDModule extends SimpleModule {

	public XSDModule() {
		super("XSDModule", new Version(0,0,1,null));
	}


	@Override
	public void setupModule(SetupContext context) {
		super.setupModule(context);
		AnnotationIntrospector jaxbIntrospector = new JaxbAnnotationIntrospector();
		context.appendAnnotationIntrospector(jaxbIntrospector);
	}

}

