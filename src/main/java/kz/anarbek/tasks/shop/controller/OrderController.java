/*
 * JBoss, Home of Professional Open Source
 * Copyright 2013, Red Hat, Inc. and/or its affiliates, and individual
 * contributors by the @authors tag. See the copyright.txt in the
 * distribution for a full listing of individual contributors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package kz.anarbek.tasks.shop.controller;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.enterprise.inject.Produces;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import kz.anarbek.tasks.shop.data.CartListProducer;
import kz.anarbek.tasks.shop.model.OrderInfo;

@Model
public class OrderController {

	@Inject
	private FacesContext facesContext;

	@Inject
	private CartListProducer cartListProducer;

	@Produces
	@Named
	private OrderInfo orderInfo;

	@PostConstruct
	public void init() {
		orderInfo = cartListProducer.getOrderInfo();
	}

	public void add(Long productId) throws Exception {
		try {
			cartListProducer.add(productId);
			FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_INFO, "Added!", "Added to cart successfully");
			facesContext.addMessage(null, m);
		} catch (Exception e) {
			FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getLocalizedMessage(),
					"Not added to Cart");
			facesContext.addMessage(null, m);
		}
	}

	public String checkout() throws Exception {
		try {
			cartListProducer.checkout(orderInfo);
			orderInfo = cartListProducer.getOrderInfo();
			FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_INFO, "Thank you for your purchase. Your order is sent for processing, and will shortly be sent. ", "");
			facesContext.addMessage(null, m);
		} catch (Exception e) {
			FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getLocalizedMessage(),
					"Ordering error");
			facesContext.addMessage(null, m);
		}

		return "confirm";
	}
}
