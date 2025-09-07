

package apisustentavel.fullstack.validation.validators;

import apisustentavel.fullstack.validation.annotations.ValidCPF;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class CpfValidator implements ConstraintValidator<ValidCPF, String>
{

    @Override
    public void initialize(ValidCPF constraintAnnotation)
    {
    }

    @Override
    public boolean isValid(String cpf, ConstraintValidatorContext context)
    {
        if (cpf == null || cpf.isEmpty())
        {
            return false;
        }

        // Remove caracteres não numéricos
        cpf = cpf.replaceAll("[^0-9]", "");

        // Verifica se tem 11 dígitos
        if (cpf.length() != 11)
        {
            return false;
        }

        // Verifica se todos os dígitos são iguais (ex: 111.111.111-11)
        if (cpf.matches("(\\d)\\1{10}"))
        {
            return false;
        }

        // Validação do CPF
        try
        {
            int[] digits = new int[11];
            for (int i = 0; i < 11; i++)
            {
                digits[i] = Integer.parseInt(cpf.substring(i, i + 1));
            }

            int v1 = 0, v2 = 0;
            for (int i = 0; i < 9; i++)
            {
                v1 += digits[i] * (10 - i);
                v2 += digits[i] * (11 - i);
            }
            v1 = 11 - (v1 % 11);
            if (v1 >= 10) v1 = 0;
            v2 += v1 * 2;
            v2 = 11 - (v2 % 11);
            if (v2 >= 10) v2 = 0;

            return v1 == digits[9] && v2 == digits[10];
        }catch (NumberFormatException e)
        {
            return false;
        }
    }
}

