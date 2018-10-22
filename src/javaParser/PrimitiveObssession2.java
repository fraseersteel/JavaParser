package javaParser;


import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.FieldDeclaration;
import com.github.javaparser.ast.body.VariableDeclarator;
import com.github.javaparser.ast.expr.VariableDeclarationExpr;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import java.io.File;

public class PrimitiveObssession2 {

    public void run(File file) throws Exception{
        CompilationUnit cu = JavaParser.parse(file);
        PrimitiveVisitor pv = new PrimitiveVisitor();
        cu.accept(pv, null);
        pv.calPrimPercentage();
    }

    private static class PrimitiveVisitor extends VoidVisitorAdapter<Void> {

        private List<String> primitiveVarName = new ArrayList<>();
        private int primitiveCount = 0;
        private int variableCount = 0;

        public void visit(ClassOrInterfaceDeclaration c,  Void arg){

        }

        public float calPrimPercentage(){

            return 0;
        }
    }
}
