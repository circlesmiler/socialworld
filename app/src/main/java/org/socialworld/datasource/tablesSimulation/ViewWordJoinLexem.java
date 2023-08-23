package org.socialworld.datasource.tablesSimulation;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.socialworld.datasource.mariaDB.Table;

public class ViewWordJoinLexem extends Table {

	public final  String 	ALL_COLUMNS 		=	" word_id, word, lexem_id, tense, numerus, pronoun_word_id, subjectable, type ";
	public final  int 		SELECT_ALL_COLUMNS 	= 1;

	int word_id[];
	String word[];
	int lexem_id[];
	int tense[];
	int numerus[];
	int pronoun_word_id[];
	int type[];
	int subjectable[];
	
	@Override
	protected String getTableName() {
		return "sw_v_word_lexem";
	}

	@Override
	protected String getSelectList(int selectList) {
		switch (selectList) {
		case SELECT_ALL_COLUMNS:
			return  ALL_COLUMNS;
		default:
			return ALL_COLUMNS;
		}
	}

	@Override
	public void select(String statement) {
		ResultSet rs;
		
		rs = connection.executeQuery(statement);
		
		
		switch (selectList) {
		
		case SELECT_ALL_COLUMNS:
			selectAllColumns(rs);

			break;
		default:
			selectAllColumns(rs);
		}
		setPK1(word_id);
	}


	private void selectAllColumns(ResultSet rs) {
		int row = 0;
		word_id = new int[rowCount];
		word = new String[rowCount];
		lexem_id = new int[rowCount];
		tense = new int[rowCount];
		numerus = new int[rowCount];
		pronoun_word_id = new int[rowCount];
		subjectable = new int[rowCount];
		type = new int[rowCount];

		try {
			while (rs.next()) {
				
				word_id[row] = rs.getInt(1);
				word[row] = rs.getString(2);
				lexem_id[row] = rs.getInt(3);
				tense[row] = rs.getInt(4);
				numerus[row] = rs.getInt(5);
				pronoun_word_id[row] = rs.getInt(6);
				subjectable[row] = rs.getInt(7);
				type[row] = rs.getInt(8);
				
				row++;
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
			return;
		}

	}
	
	
	
	public int getWordID(int index) {
		return word_id[index];
	}

	public String getWord(int index) {
		return word[index];
	}
	
	public int getLexemID(int index) {
		return lexem_id[index];
	}

	public int getTense(int index) {
		return tense[index];
	}
	
	public int getNumerus(int index) {
		return numerus[index];
	}

	public int getPronounWordID(int index) {
		return pronoun_word_id[index];
	}

	public int getSubjectable(int index) {
		return subjectable[index];
	}
	
	public int getType(int index) {
		return type[index];
	}

}