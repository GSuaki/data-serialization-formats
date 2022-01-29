package com.gsuaki.avro;

import java.io.IOException;
import org.apache.avro.Schema;
import org.apache.avro.io.DatumReader;
import org.apache.avro.io.Decoder;
import org.apache.avro.io.DecoderFactory;
import org.apache.avro.specific.SpecificDatumReader;

public class AvroDeserializer {

  public static <T> T fromBinaryJson(byte[] data, final Class<T> clz, final Schema schema) {
    try {
      final DatumReader<T> reader = new SpecificDatumReader<>(clz);
      final Decoder decoder = DecoderFactory.get().jsonDecoder(schema, new String(data));
      return reader.read(null, decoder);
    } catch (IOException e) {
      e.printStackTrace();
      throw new RuntimeException(e);
    }
  }

  public static <T> T fromBinary(final byte[] data, final Class<T> clz) {
    try {
      final DatumReader<T> employeeReader = new SpecificDatumReader<>(clz);
      Decoder decoder = DecoderFactory.get().binaryDecoder(data, null);
      return employeeReader.read(null, decoder);
    } catch (IOException e) {
      e.printStackTrace();
      throw new RuntimeException(e);
    }
  }
}
