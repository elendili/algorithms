# https://www.hackerrank.com/challenges/is-binary-search-tree/proble


def in_order(root, acc=[]):
    if not root:
        return True
    out = in_order(root.left, acc)
    if not out or (len(acc) > 1 and acc[-2] >= acc[-1]):
        return False
    acc.append(root.data)
    if len(acc) > 1 and acc[-2] >= acc[-1]:
        return False
    out = in_order(root.right, acc)
    if not out or (len(acc) > 1 and acc[-2] >= acc[-1]):
        return False

    return True


def check_binary_search_tree_(root):
    return in_order(root)
