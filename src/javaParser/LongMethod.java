package javaParser;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.NodeList;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.stmt.*;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

import java.io.File;
import java.util.NoSuchElementException;

public class LongMethod {

    static int maxNumberStatements = 10;

    public void run(File file) throws Exception {

        CompilationUnit cu = JavaParser.parse(file);
        MethodStatementVisitor msv = new MethodStatementVisitor();

        cu.accept(msv, null);
    }


    private static class MethodStatementVisitor extends VoidVisitorAdapter<Void> {

        private int numStatements;

        public void visit(MethodDeclaration n, Void arg) {
            numStatements = 0;
            try {
                visit(n.getBody().get(), arg);

                if (numStatements > maxNumberStatements) {
                    System.out.println("Fail! Method '" + n.getName() + "' has too many statements [" + numStatements + "]");
                } else {
                    System.out.println("Pass. Method '" + n.getName() + "' [" + numStatements + "]");
                }
            } catch (NoSuchElementException e) {
                System.out.println("Method " + n.getName() + " contains no body.");
            }

            super.visit(n, arg);
        }

        public void visit(BlockStmt n, Void arg) {
            NodeList<Statement> statements = n.getStatements();
            numStatements = numStatements + statements.size();

            super.visit(n, arg);
        }

    }
}