package javaParser;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.NodeList;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.ConstructorDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.stmt.*;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

import java.io.File;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Optional;

public class LargeClass {

    public void run(File file) throws Exception {

        CompilationUnit cu = JavaParser.parse(file);
        LargeClassStatements lcs = new LargeClassStatements();

        cu.accept(lcs, null);
        System.out.println(lcs.getCount() + " statements ");
        if (lcs.isAcceptable() == false) {
            System.out.println("FAIL - Too many statements in class" + file.getName());
        } else {
            System.out.println("PASS");
        }
    }


    private static class LargeClassStatements extends VoidVisitorAdapter<Void> {

        int count = 0;

        public void visit(ClassOrInterfaceDeclaration m, Void arg) {

            // fields
            count+= m.getFields().size();

            // constructors
            for (ConstructorDeclaration con : m.getConstructors()) {
                BlockStmt conBlock = con.getBody();
                NodeList<Statement> conStatements = conBlock.getStatements();
                count+= conStatements.size();
            }
            try {
                for (MethodDeclaration currentMethod : m.getMethods()) {
                    Optional<BlockStmt> mBlock = currentMethod.getBody();
                    NodeList<Statement> methStatements = mBlock.get().getStatements();
                    count = count + methStatements.size();
                    visit(currentMethod.getBody().get(), arg);
                }
            } catch (NoSuchElementException e) {
            }
        }

        public void visit(BlockStmt blockStmt, Void arg) {
            NodeList<Statement> statements = blockStmt.getStatements();
            count = count + statements.size();
        }

        public int getCount() {
            return count;
        }

        public boolean isAcceptable() {
            if (getCount() >= 100) {
                return false;
            } else {
                return true;
            }
        }


    }
}
