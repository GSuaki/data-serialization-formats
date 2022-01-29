package com.gsuaki.avro;

import java.io.File;
import java.io.IOException;
import org.apache.avro.Schema;
import org.apache.avro.Schema.Parser;
import org.apache.avro.compiler.specific.SpecificCompiler;

public class AvroClassGenerator {

  public static void generateAvroClasses(final String file) throws IOException {
    final Schema schema = new Parser().parse(new File("src/main/resources/avro/" + file));

    final SpecificCompiler compiler = new SpecificCompiler(schema);
    compiler.compileToDestination(new File("src/main/resources"), new File("src/main/java"));
  }
}
