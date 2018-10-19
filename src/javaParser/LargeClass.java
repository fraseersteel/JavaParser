package javaParser;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.NodeList;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.ConstructorDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.stmt.BlockStmt;
import com.github.javaparser.ast.stmt.IfStmt;
import com.github.javaparser.ast.stmt.Statement;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

import java.io.File;
import java.util.ArrayList;
import java.util.Optional;

public class LargeClass {

    public void run(File file) throws Exception {

        CompilationUnit cu = JavaParser.parse(file);
        LargeClassStatments lcs = new LargeClassStatments();

        cu.accept(lcs, null);
    }


    private static class LargeClassStatments extends VoidVisitorAdapter {

        int count = 0;

        public void visit(ClassOrInterfaceDeclaration c, Object arg) {
            count = count + c.getFields().size();
            System.out.println("number of method " + c.getMethods().size());
            System.out.println("number of fields " + c.getFields().size());
            System.out.println("number of constructors " + c.getConstructors().size());

            for (MethodDeclaration m : c.getMethods()) {
                Optional<BlockStmt> block = m.getBody();
                NodeList<Statement> statements = block.get().getStatements();
                count = count + statements.size();
                

            for(ConstructorDeclaration con : c.getConstructors()){
                BlockStmt conBlock = con.getBody();
                NodeList<Statement> conStatements = conBlock.getStatements();
                count = count + statements.size();
            }

//            for(IfStmt ifStmt : c.){
//                Optional<BlockStmt> block = m.getBody();
//                NodeList<Statement> statements = block.get().getC
//            }

                System.out.println("count " + count);


            }


        }

        public int getCount(){
            return count;
        }

        public boolean isAcceptable(){
            if(getCount()<100){
                return true;
            }else{
                return false;
            }
        }


    }
}
