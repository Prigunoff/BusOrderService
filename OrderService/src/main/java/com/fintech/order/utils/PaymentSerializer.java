package com.fintech.order.utils;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.fintech.order.model.Payment;

import java.io.IOException;

public class PaymentSerializer extends StdSerializer<Payment> {
    public PaymentSerializer(Class<Payment> t) {
        super(t);
    }
    public PaymentSerializer() {
        this(null);
    }


    @Override
    public void serialize(Payment payment, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeNumberField("id", payment.getId());
        jsonGenerator.writeEndObject();
    }
}
