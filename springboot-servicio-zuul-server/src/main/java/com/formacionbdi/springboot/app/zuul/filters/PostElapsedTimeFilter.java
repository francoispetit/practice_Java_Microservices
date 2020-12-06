package com.formacionbdi.springboot.app.zuul.filters;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.springframework.stereotype.Component;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

@Component
public class PostElapsedTimeFilter extends ZuulFilter {

	private static Logger log = org.slf4j.LoggerFactory.getLogger(PostElapsedTimeFilter.class);
	
	@Override
	public boolean shouldFilter() {
		return true;
	}

	@Override
	public Object run() throws ZuulException {
		RequestContext ctx = RequestContext.getCurrentContext();
		HttpServletRequest request = ctx.getRequest();
		
		log.info("Entering post filter");
		
		Long beginTime = (Long) request.getAttribute("beginTime");
		Long endTime = System.currentTimeMillis();
		Long elapsedTime = endTime - beginTime;
		
		log.info(String.format("Elapsed time : %s sec.", elapsedTime.doubleValue()/1000.00));
		
		return null;
	}

	@Override
	public String filterType() {
		return "post";
	}

	@Override
	public int filterOrder() {
		return 1;
	}

}
