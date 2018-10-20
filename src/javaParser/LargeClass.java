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
import java.util.Optional;

public class LargeClass {

    public void run(File file) throws Exception {

        CompilationUnit cu = JavaParser.parse(file);
        LargeClassStatements lcs = new LargeClassStatements();

        cu.accept(lcs, null);
        System.out.println(lcs.getCount() + " statements ");
        if(lcs.isAcceptable()==false){
            System.out.println("FAIL - Too many statements in class" + file.getName());
        }else{
            System.out.println("PASS");
        }
    }



    private static class LargeClassStatements extends VoidVisitorAdapter {

        int count = 0;


        public void visit(ClassOrInterfaceDeclaration c, Object arg) {
            count = count + c.getFields().size();
            System.out.println("number of method " + c.getMethods().size());
            System.out.println("number of fields " + c.getFields().size());
            System.out.println("number of constructors " + c.getConstructors().size());

            //method statements
            for (MethodDeclaration method : c.getMethods()) {
                Optional<BlockStmt> mBlock = method.getBody();
                NodeList<Statement> methStatements = mBlock.get().getStatements();
                System.out.println(methStatements);
                count = count + methStatements.size();

            }

            //constructors
            for (ConstructorDeclaration con : c.getConstructors()) {
                BlockStmt conBlock = con.getBody();
                NodeList<Statement> conStatements = conBlock.getStatements();
                count = count + conStatements.size();
            }


            for(IfStmt child : c.getParentNodeForChildren().getChildNodesByType((IfStmt.class)) ) {
                count++;

                visit(child, null);
                if (child.getElseStmt().isPresent()) {
                    if (child.getElseStmt().get() instanceof IfStmt) {
                    } else {
                        count++;
                        visit(child, null);
                    }
                }
            }

//            for(ForStmt child : c.getMethodsByName())


            for(WhileStmt child : c.getParentNodeForChildren().getChildNodesByType(WhileStmt.class)){
                System.out.println(child.getCondition() + " condition");
                ArrayList<Statement> methStatements = new ArrayList<>();
                methStatements.add(child.getBody());
                count = count + methStatements.size();
                count++;
            }


        }


        public int getCount() {
            return count;
        }

        public boolean isAcceptable() {
            if (getCount()>= 20) {
                return false;
            } else {
                return true;
            }
        }


    }
}
