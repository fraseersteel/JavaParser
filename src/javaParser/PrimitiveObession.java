package javaParser;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;

import java.io.File;

public class PrimitiveObession {

    public void run(File file) throws Exception{

        CompilationUnit cu = JavaParser.parse(file);

    }
}
