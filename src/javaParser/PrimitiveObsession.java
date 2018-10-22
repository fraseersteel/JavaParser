package javaParser;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.NodeList;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.FieldDeclaration;
import com.github.javaparser.ast.body.VariableDeclarator;
import com.github.javaparser.ast.expr.VariableDeclarationExpr;
import com.github.javaparser.ast.stmt.BlockStmt;
import com.github.javaparser.ast.stmt.Statement;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

import javax.swing.plaf.nimbus.State;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class PrimitiveObsession {

    public void run(File file) throws Exception {

        CompilationUnit cu = JavaParser.parse(file);
        PrimitiveVisitor pv = new PrimitiveVisitor();
        cu.accept(pv, null);
        pv.calPrimPercentage();

    }


    private static class PrimitiveVisitor extends VoidVisitorAdapter<Void> {

        private List<String> primitiveVarName = new ArrayList<>();
        private int primitiveCount = 0;
        private int variableCount = 0;

        public void visit(ClassOrInterfaceDeclaration c, Void arg) {

            for (FieldDeclaration v : c.getFields()) {
                if (v.getCommonType().isPrimitiveType()) {
                    if (!primitiveVarName.contains(v)) {
                        primitiveVarName.add(v.toString());
                        primitiveCount++;
                        variableCount++;
                    } else {
                        System.out.println("already contains - " + v);
                    }
                } else {
                    variableCount++;
                }
            }
            super.visit(c,arg);
        }

        public void visit(VariableDeclarationExpr vd, Void arg) {

            for (VariableDeclarator v : vd.getVariables()) {
                if (v.getType().isPrimitiveType()) {
                    if (!primitiveVarName.add(v.toString()));
                    primitiveCount++;
                }
            }
            super.visit(vd, arg);
        }

        public void visit(BlockStmt n, Void arg){
            NodeList<Statement> statements = n.getStatements();
            for(int i = 0; i>statements.size();i++){
                if(statements.get(i).isLocalClassDeclarationStmt()){
                    variableCount++;
                }
            }

        }

        private void calPrimPercentage() {
            System.out.println("calculating");
            if (primitiveCount > 0 && variableCount>0) {
                System.out.println("calculating percentage " + primitiveCount + "/" + variableCount + "*100");
                float primPercentage = primitiveCount * 100 / variableCount;
                if (primPercentage >= 70) {
                    System.out.println("Fail - Focuses on too many primitives"  + "[" + primPercentage + "]");
                } else {
                    System.out.println("Pass" + "[" + primPercentage + "]");
                }
            }else{
                System.out.println("no primitives");
                System.out.println(variableCount + " variable count");
            }
        }
    }
}

