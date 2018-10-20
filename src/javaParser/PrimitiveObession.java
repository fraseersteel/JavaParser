package javaParser;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.type.ClassOrInterfaceType;
import com.github.javaparser.ast.type.PrimitiveType;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

import java.io.File;
import java.util.ArrayList;

public class PrimitiveObession {

    public void run(File file) throws Exception{

        CompilationUnit cu = JavaParser.parse(file);

    }


//    private static class PrimitiveVisitor extends VoidVisitorAdapter{
//        public void visit(PrimitiveType c, Object arg){
//
//            ArrayList<PrimitiveType> list = new ArrayList<>();
//
//            for(PrimitiveType p : c.getChildNodes().);
//        }
//    }
}
