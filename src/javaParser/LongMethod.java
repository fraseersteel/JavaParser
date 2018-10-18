
package javaParser;

import java.io.File;
import java.util.Optional;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.NodeList;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.stmt.BlockStmt;
import com.github.javaparser.ast.stmt.Statement;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;


public class LongMethod {

    public void run(File file) throws Exception {

        CompilationUnit cu = JavaParser.parse(file);

        MethodDeclarationVisitor md = new MethodDeclarationVisitor();
        cu.accept(md, null);


    }


    private static class MethodDeclarationVisitor extends VoidVisitorAdapter {
        public void visit(MethodDeclaration m, Object arg) {

            Optional<BlockStmt> block = m.getBody();
            NodeList<Statement> statements = block.get().getStatements();

            System.out.println("no. of statements " + statements.size());

            if (statements.size() > 10) {
                System.out.println("Error with [" + m.getName() + "] " + "more than 10 statements");
            }
        }

    }
}

