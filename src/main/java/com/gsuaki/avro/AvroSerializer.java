package com.gsuaki.avro;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import org.apache.avro.Schema;
import org.apache.avro.io.DatumWriter;
import org.apache.avro.io.Encoder;
import org.apache.avro.io.EncoderFactory;
import org.apache.avro.specific.SpecificDatumWriter;

public class AvroSerializer {
  public static <T> byte[] toBinary(final T request, final Class<T> clz) {
    final byte[] data;

    try (final ByteArrayOutputStream stream = new ByteArrayOutputStream()) {
      final Encoder jsonEncoder = EncoderFactory.get().binaryEncoder(stream, null);

      final DatumWriter<T> writer = new SpecificDatumWriter<>(clz);
      writer.write(request, jsonEncoder);

      jsonEncoder.flush();
      data = stream.toByteArray();
    } catch (IOException e) {
      e.printStackTrace();
      throw new RuntimeException(e);
    }

    return data;
  }

  public static <T> byte[] toBinaryJson(final T request, final Class<T> clz, final Schema schema) {
    final byte[] data;

    try (final ByteArrayOutputStream stream = new ByteArrayOutputStream()) {
      final Encoder jsonEncoder = EncoderFactory.get().jsonEncoder(schema, stream);

      final DatumWriter<T> writer = new SpecificDatumWriter<>(clz);
      writer.write(request, jsonEncoder);

      jsonEncoder.flush();
      data = stream.toByteArray();
    } catch (IOException e) {
      e.printStackTrace();
      throw new RuntimeException(e);
    }

    return data;
  }
}
