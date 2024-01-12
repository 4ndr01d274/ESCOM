(defun producto (lis)
           (cond ((null lis) 1)
                 (T (* (car lis) (producto (cdr lis))))))