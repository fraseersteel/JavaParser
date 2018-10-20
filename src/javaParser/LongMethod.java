
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

        if(md.getPassOrFail()==false){
            System.out.println("All methods passed");
        }

    }


    private static class MethodDeclarationVisitor extends VoidVisitorAdapter {

        boolean fail = false;
        public void visit(MethodDeclaration m, Object arg) {

            Optional<BlockStmt> block = m.getBody();
            NodeList<Statement> statements = block.get().getStatements();
            if (statements.size() > 10) {
                System.out.println("Error with [" + m.getName() + "] " + "more than 10 statements");
                fail = true;
            }
        }

        boolean getPassOrFail(){
            return fail;
        }

    }
}

