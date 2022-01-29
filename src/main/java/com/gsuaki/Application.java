package com.gsuaki;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.gsuaki.avro.AvroClassGenerator;
import com.gsuaki.avro.AvroSerializer;
import com.gsuaki.avro.models.ArtistRecord;
import com.gsuaki.models.Artist;
import com.gsuaki.utils.BinaryUtils;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;
import org.msgpack.jackson.dataformat.MessagePackFactory;

public class Application {

  public static void main(String[] args) throws Exception {
    printJsonBinary();
    printMessagePackBinary();
    printAvroBinary();
  }

  private static void printJsonBinary() throws Exception {
    final ObjectMapper mapper = new ObjectMapper();
    mapper.setPropertyNamingStrategy(PropertyNamingStrategies.SNAKE_CASE);

    final Artist artist = new Artist("Daft Punk", 1000, List.of("Orlando", "Nova York"));

    final String json = mapper.writeValueAsString(artist);

    System.out.printf("JSON Hex (size: %s)\n", json.getBytes(StandardCharsets.UTF_8).length);
    System.out.println(json);
    System.out.println();
  }

  private static void printMessagePackBinary() throws Exception {
    final ObjectMapper mapper = new ObjectMapper(new MessagePackFactory());
    mapper.setPropertyNamingStrategy(PropertyNamingStrategies.SNAKE_CASE);

    final Artist artist = new Artist("Daft Punk", 1000, List.of("Orlando", "Nova York"));

    final byte[] bytes = mapper.writeValueAsBytes(artist);
    final String hex = BinaryUtils.bytesToHex(bytes);

    System.out.printf("MessagePack Hex (size: %s)\n", bytes.length);
    System.out.println(hex);
    System.out.println();
    // 83 | ab | 61 72 74 69 73 74 5f 6e 61 6d 65 | a9 | 44 61 66 74 20 50 75 6e 6b |
    //    | a5 | 63 61 63 68 65 | cd | 03 e8 |
    //    | a6 | 63 69 74 69 65 73
    // 92 | a7 | 4f 72 6c 61 6e 64 6f
    //    | a9 | 4e 6f 76 61 20 59 6f 72 6b
  }

  private static void printAvroBinary() throws Exception {
    AvroClassGenerator.generateAvroClasses("artist-schema.avsc");

    final ArtistRecord artist = new ArtistRecord();
    artist.setArtistName("Daft Punk");
    artist.setCache(1000);
    artist.setCities(Arrays.asList("Orlando", "Nova York"));

    final byte[] bytes = AvroSerializer.toBinary(artist, ArtistRecord.class);
    final String hex = BinaryUtils.bytesToHex(bytes);

    System.out.printf("Avro Hex (size: %s)\n", bytes.length);
    System.out.println(hex);
    System.out.println();
    // 12 | 44 61 66 74 20 50 75 6E 6B |
    // D0 | 0F |
    // 04 |
    // 0E | 4F 72 6C 61 6E 64 6F
    // 12 | 4E 6F 76 61 20 59 6F 72 6B
    // 00
  }
}