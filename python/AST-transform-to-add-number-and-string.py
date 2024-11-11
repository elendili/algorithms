import ast
import sys
import types

class AdditionTransformer(ast.NodeTransformer):
    def visit_BinOp(self, node):
        # Recursively visit child nodes
        self.generic_visit(node)

        # Check if the operation is addition
        if isinstance(node.op, ast.Add):
            # Check for 'str' + int
            if isinstance(node.left, ast.Str) and isinstance(node.right, (ast.Num, ast.Constant)):
                if isinstance(node.right.value, int):
                    new_node = ast.BinOp(
                        left=ast.Call(
                            func=ast.Name(id='int', ctx=ast.Load()),
                            args=[node.left],
                            keywords=[]
                        ),
                        op=ast.Add(),
                        right=node.right
                    )
                    return ast.copy_location(new_node, node)

            # Check for int + 'str'
            if isinstance(node.left, (ast.Num, ast.Constant)) and isinstance(node.right, ast.Str):
                if isinstance(node.left.value, int):
                    new_node = ast.BinOp(
                        left=node.left,
                        op=ast.Add(),
                        right=ast.Call(
                            func=ast.Name(id='int', ctx=ast.Load()),
                            args=[node.right],
                            keywords=[]
                        )
                    )
                    return ast.copy_location(new_node, node)

        return node

def exec_with_transformation(code_str, globals=None, locals=None):
    tree = ast.parse(code_str)
    transformer = AdditionTransformer()
    tree = transformer.visit(tree)
    ast.fix_missing_locations(tree)
    code = compile(tree, '<string>', 'exec')
    exec(code, globals, locals)

# Example usage
code = """
result = '2' + 2
print(result)  # This will print 4
"""

exec_with_transformation(code)
