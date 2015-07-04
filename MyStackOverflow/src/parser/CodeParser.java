package parser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.ASTParser;
import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.ClassInstanceCreation;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.MethodInvocation;
import org.eclipse.jdt.core.dom.QualifiedName;
import org.eclipse.jdt.core.dom.SimpleName;
import org.eclipse.jdt.core.dom.SimpleType;
import org.eclipse.jdt.core.dom.VariableDeclarationFragment;

/**
 * @author thanhnguyen
 * Created on June, 21
 */

public class CodeParser {
	 
		// use ASTParse to parse string
		public static void parse(String str) {
			ASTParser parser = ASTParser.newParser(AST.JLS8);
			parser.setSource(str.toCharArray());
			parser.setKind(ASTParser.K_COMPILATION_UNIT);
	 
			final CompilationUnit cu = (CompilationUnit) parser.createAST(null);
	 
			cu.accept(new ASTVisitor() {
	 
				@SuppressWarnings("rawtypes")
				Set names = new HashSet();
				
				public void preVisit(ASTNode astNode) {
					System.out.println("Visiting: " + astNode.getClass().getSimpleName() + " " + astNode.toString());
				}
	 
				@SuppressWarnings("unchecked")
				public boolean visit(VariableDeclarationFragment node) {
					SimpleName name = node.getName();
					this.names.add(name.getIdentifier());
					System.out.println("Declaration of '" + name + " " + node.getInitializer() + "' at line"
							+ cu.getLineNumber(name.getStartPosition()));
					return true; // do not continue 
				}
	 
				public boolean visit(SimpleName node) {
					if (this.names.contains(node.getIdentifier())) {
						System.out.println("Usage of '" + node + "\t" + node.getIdentifier() + "' at line "
								+ cu.getLineNumber(node.getStartPosition()));
					}
					return true;
				}
				
				public boolean visit(SimpleType node) {
					// Simple Type , class =>kind: type, qualified name = name
					System.out.println("Type: " + node.toString() + " " + node + "\t Kind: type");
					
					return true;
				}
				
				public boolean visit(MethodInvocation node) {
					System.out.println("Method Call: " + node.getName() + "\t Kind: method " + node.getExpression());
					return true;
				}
				
				public boolean visit(QualifiedName node) {
					System.out.println("Qualified Name: " + node.getName() + "\t" + node.getQualifier() + "\t Kind: field");
					return true;
				}
				
				public boolean visit(ClassInstanceCreation node) {
					System.out.println("Instance Creation: " + node.toString() + "\t" + node.getType());
					return true;
				}
			});
	 
		}
	 
		// read file content into a string
		public static String readFileToString(String filePath) throws IOException {
			StringBuilder fileData = new StringBuilder(1000);
			BufferedReader reader = new BufferedReader(new FileReader(filePath));
	 
			char[] buf = new char[10];
			int numRead = 0;
			while ((numRead = reader.read(buf)) != -1) {
//				System.out.println(numRead);
				String readData = String.valueOf(buf, 0, numRead);
				fileData.append(readData);
				buf = new char[1024];
			}
	 
			reader.close();
	 
			return  fileData.toString();	
		}
	 
		//loop directory to get file list
		public static void ParseFilesInDir() throws IOException{
			File dirs = new File(".");
			String dirPath = dirs.getCanonicalPath() + File.separator+"test" + File.separator;
	 
			File root = new File(dirPath);
			//System.out.println(rootDir.listFiles());
			File[] files = root.listFiles ( );
			String filePath = null;
	 
			 for (File f : files ) {
				 filePath = f.getAbsolutePath();
				 if(f.isFile()){
					 parse(readFileToString(filePath));
				 }
			 }
		}
	 
		public static void main(String[] args) throws IOException {
			ParseFilesInDir();
		}
}




