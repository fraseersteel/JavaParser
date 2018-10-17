
package javaParser;

import java.io.FileInputStream;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.InitializerDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.expr.AssignExpr;
import com.github.javaparser.ast.expr.ObjectCreationExpr;
import com.github.javaparser.ast.expr.VariableDeclarationExpr;
import com.github.javaparser.ast.stmt.IfStmt;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;


public class LargeClass {

    public static void main(String[] args) throws Exception {
        FileInputStream in = new FileInputStream("src/TestFolder/SimpleTests/SimpleClass.java");

        CompilationUnit cu;
        try {
            cu = JavaParser.parse(in);
        } finally {
            in.close();
        }
        new VariableDeclaratorVisitor().visit(cu, null);
        new AssignVisitor().visit(cu, null);
        new ControlStatementVisitor().visit(cu, null);

    }

    /**
     * Example to demonstrate how to control the way that visitors move about
     * Assume we wanted to find all assignment expressions associated with each method
     */
//    private static class MethodVisitor extends VoidVisitorAdapter {
//
//        @Override
//        public void visit(MethodDeclaration n, Object arg) {
//            System.out.println("Method name: " + n.getName());
//            // Call accept on the instance of the method body and pass it the visitor
//            // defined in the class below (which doesn't otherwise get called)
//            n.getBody().get().accept(new BodyVisitor(), arg);
//        }
//    }


    private static class AssignVisitor extends VoidVisitorAdapter {
        public void visit(AssignExpr p, Object arg) {
            System.out.println("Assignment statement:" + p.toString());
        }
    }

    private static class VariableDeclaratorVisitor extends VoidVisitorAdapter {
        public void visit(VariableDeclarationExpr i, Object arg) {
            System.out.println("Initialzier statement: " + i.toString());
        }
    }

    private static class ControlStatementVisitor extends VoidVisitorAdapter {
        public void visit(IfStmt ifSt, Object arg) {
            System.out.println("control : " + ifSt.toString());
        }
    }

//    private static class DeclarationVisitor extends  VoidVisitorAdapter{
//        public void visit()
//    }
}
