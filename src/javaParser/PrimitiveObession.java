package javaParser;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.type.PrimitiveType;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

import java.io.File;
import java.util.ArrayList;

public class PrimitiveObession {

    public void run(File file) throws Exception{

        CompilationUnit cu = JavaParser.parse(file);

    }
    

//    private static class PrimitiveVisitor extends VoidVisitorAdapter{
//        public void visit(PrimitiveType p,Object arg){
//
//            ArrayList<PrimitiveType> list = new ArrayList<>();
//            for(PrimitiveType )
//        }
//    }
}
