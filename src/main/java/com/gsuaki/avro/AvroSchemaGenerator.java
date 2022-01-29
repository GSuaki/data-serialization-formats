package com.gsuaki.avro;

import java.util.ArrayList;
import org.apache.avro.Schema;
import org.apache.avro.SchemaBuilder;

public class AvroSchemaGenerator {

  public static Schema createAvroSchema() {
    final Schema artist = SchemaBuilder.record("ArtistRecord")
        .namespace("com.gsuaki.avro.models")
        .fields()
        .requiredString("artistName")
        .requiredInt("cache")
        .name("cities").type().array().items().stringType().arrayDefault(new ArrayList<>())
        .endRecord();

    return artist;
  }
}
