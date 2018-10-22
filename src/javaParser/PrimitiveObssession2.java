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

            for(FieldDeclaration f : c.getFields()){
                if(f.getElementType().isPrimitiveType()){
                    if(!primitiveVarName.contains(f)){
                        primitiveVarName.add(f.toString());
                        primitiveCount++;
                        variableCount++;
                    } else{
                        System.out.println("already contains -- " + f);
                    }
                }else{
                    variableCount++;
                }
            }
        }

        private void calPrimPercentage() {
            System.out.println("calculating");
            if (primitiveCount < 0) {
                System.out.println("calculating percentage");
                float primPercentage = primitiveCount * 100 / variableCount;
                if (primPercentage <= 70) {
                    System.out.println("Fail - Focuses on too many primitives");
                } else {
                    System.out.println("Pass");
                }
            }else{
                System.out.println("no primitives");
                System.out.println(variableCount + " variable count");
            }
        }
    }
}
