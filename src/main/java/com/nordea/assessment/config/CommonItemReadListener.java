/*
 * Listener to handle Exception occurs in IteamReader
 * @Auther : Prabhu Sahu
 * */

package com.nordea.assessment.config;

import org.springframework.batch.core.ItemReadListener;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class CommonItemReadListener implements ItemReadListener<Object> {

   
    // omitted.

    // (1)
    @Override
    public void onReadError(Exception ex) {
        log.error("Exception occurred while reading from IteamReader.", ex);  // (2)
    }

	@Override
	public void beforeRead() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void afterRead(Object item) {
		// TODO Auto-generated method stub
		
	}
    // omitted.
}
