package test.designpattern.templatemethod;

import test.designpattern.templatemethod.bean.Document;

/**
 * @author Vida
 * 
 *         ���ΡG�N�U����󪺰��ݩ� or�ɮ׸��...���~�ߵ����ݭn�����
 * 
 *         Design Pattern - Template Method(�˪O��k�Ҧ�)
 * 
 *         �w�q�G�N�@�Ӻt��k�����[�w�q�b�@�Ӥ�k���A�Ӻt��k�����|�Ψ쪺�@�Ǥ�k
 *         �A�h�w�q�b�����O���C�˪O��k�������O�i�H�b�����ܺt��k�[�c�����p�U�A���s�w�q�t��k�����Y�ǨB�J�C
 * 
 *         �n�I1�B�G�N�˪O��k(���[)�w�q�b�����O���A�q�`�i�H�ŧi����k��final�A�p���i�קK�l���Ooverride�䤤���t��k
 *         �n�I2�B�G�����O����H���O�A�q�`�Ρu�~�ӡv���覡�ӫʸ˺t��k�A�o�I�P�����Ҧ��ϥΪ��u�X���v�覡���P
 *         �n�I3�B�G�i�w�q�@�Ǳ��_(Hook)��k������O���A�Ψӧ@�����󱱨�A���l���O�i��ܭn���n�v�T�����O�����t��k�y�{
 * 
 *         �ɥR1�B�G�����Ҧ�(Strategy Pattern)�w�q -
 *         �w�q�F�t��k�a�ڡA�ӧO�ʸ˰_�ӡA�����̤����i�H���۴����A���Ҧ����t��k���ܰʤ��|�v�T��ϥκt��k���{���C
 * 
 *         References:Head First Design Pattern/�`�J�L�X�]�p�Ҧ�
 */
abstract class DocumentsInterface
{
	protected final Document obj;

	DocumentsInterface(Document obj)
	{
		this.obj = obj;
		if (obj != null)
		{
			String isSpecialProcesString = checkIsSpecialType() == true ? "Y"
					: "N";
			System.out.println("�B�z���� ->" + this.obj.getDoc_type()
					+ " ,�O�_�i��S��B�z?" + isSpecialProcesString);
		}
	}

	final void processing()// ���[�t��k
	{
		if (obj != null)
		{
			sendBasicInfo();

			if (checkIsSpecialType())// Hook method,���w�]�B�z���e,�l���O�i�����poverride
			{
				doSpecialProcessing();// �l���O������@����H��k
			}

			ftpFiles();
		}
	}

	void sendBasicInfo()
	{
		System.out.println(obj.getDoc_no() + " �߰e��󸹽X�B�����B�ק������򥻸��.");
	}

	boolean checkIsSpecialType()
	{
		if ("Inner".equalsIgnoreCase(obj.getDoc_type())
				|| "BD".equalsIgnoreCase(obj.getDoc_type()))
		{
			return true;
		}
		else
		{
			return false;
		}
	}

	void ftpFiles()
	{
		System.out.println(obj.getDoc_no() + " �̩R�W�W�h���]�U���ɮצ�FTP server.");
	}

	abstract void doSpecialProcessing();
}
