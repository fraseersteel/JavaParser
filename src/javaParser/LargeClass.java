package javaParser;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.InitializerDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.expr.AssignExpr;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

import java.io.File;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.expr.AssignExpr;
import com.github.javaparser.ast.expr.ObjectCreationExpr;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;


public class LargeClass {

    int counterTotal = 0;
    final int maxStatement = 100;

    public void run(File file) throws Exception {

        CompilationUnit cu;

        cu = JavaParser.parse(file);
        MethodVisitor mv = new MethodVisitor();
        cu.accept(mv, null);
    }

    /**
     * Example to demonstrate how to control the way that visitors move about
     * Assume we wanted to find all assignment expressions associated with each method
     */
    private static class MethodVisitor extends VoidVisitorAdapter {

        int count = 0;
        int maxStatement = 100;
        boolean maxStatements = false;

        @Override
        public void visit(MethodDeclaration n, Object arg) {
//            System.out.println("Method name: " + n.getName());
            if (count < maxStatement) {
                n.getBody().get().accept(new LargeClass.BodyVisitor(), arg);
                n.getBody().get().accept(new LargeClass.InitializeVisitor(),arg);
                count++;
            } else {
                maxStatements = true;
            }
        }

    }


    private static class BodyVisitor extends VoidVisitorAdapter {
        public void visit(AssignExpr p, Object arg) {
            System.out.println("Assignment statement:" + p.toString());
        }
    }

    private static class InitializeVisitor extends VoidVisitorAdapter{
        public void visit(InitializerDeclaration iv, Object arg){
            System.out.println("Initialiser statement " + iv.toString());
        }
    }
}
